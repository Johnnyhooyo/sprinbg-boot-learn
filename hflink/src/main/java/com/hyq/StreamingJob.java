/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hyq;

import com.alibaba.fastjson.JSON;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.shaded.guava18.com.google.common.collect.Maps;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.triggers.Trigger;
import org.apache.flink.streaming.api.windowing.triggers.TriggerResult;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.util.Collector;

import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Skeleton for a Flink Streaming Job.
 *
 * <p>For a tutorial how to write a Flink streaming application, check the
 * tutorials and examples on the <a href="http://flink.apache.org/docs/stable/">Flink Website</a>.
 *
 * <p>To package your application into a JAR file for execution, run
 * 'mvn clean package' on the command line.
 *
 * <p>If you change the name of the main class (with the public static void main(String[] args))
 * method, change the respective entry in the POM.xml file (simply search for 'mainClass').
 */
public class StreamingJob {

	public static void main(String[] args) throws Exception {
		// set up the streaming execution environment
		final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

		/*
		 * Here, you can start creating your execution plan for Flink.
		 *
		 * Start with getting some data from the environment, like
		 * 	env.readTextFile(textPath);
		 *
		 * then, transform the resulting DataStream<String> using operations
		 * like
		 * 	.filter()
		 * 	.flatMap()
		 * 	.join()
		 * 	.coGroup()
		 *
		 * and many more.
		 * Have a look at the programming guide for the Java API:
		 *
		 * http://flink.apache.org/docs/latest/apis/streaming/index.html
		 *
		 */

		// execute program

		env.setParallelism(1);
		env.setStreamTimeCharacteristic(TimeCharacteristic.IngestionTime);

//		Properties properties = new Properties();
//		properties.setProperty("bootstrap.servers", "");
//		properties.setProperty("group.id", "test");
//
//
//		DataStreamSource<AntInputDto> streamSource = env.addSource(new FlinkKafkaConsumer<>("kafka-topic", new AntInputSchema(), properties));

		DataStreamSource<String> streamSource = env.socketTextStream("localhost", 9000, "\n");

		SingleOutputStreamOperator<AntInputDto> sum =
				streamSource
						.flatMap((FlatMapFunction<String, AntInputDto>) (value, out) -> {
							try {
								out.collect(JSON.parseObject(value.trim(), AntInputDto.class));

							} catch (Exception e) {
								System.out.println("非法输入");
							}
						})
						.returns(AntInputDto.class).setParallelism(1)
						.keyBy((KeySelector<AntInputDto, String>) AntInputDto::getMatchKey)
						.timeWindow(Time.seconds(30), Time.seconds(10))
						.trigger(new Trigger<AntInputDto, TimeWindow>() {
							@Override
							public TriggerResult onElement(AntInputDto element, long timestamp, TimeWindow window, TriggerContext ctx) throws Exception {
								System.out.println("window:" + window.getStart() + "-" + window.getEnd() + "; element:" + JSON.toJSONString(element)
								+ "now is " + System.currentTimeMillis());
								if (window.maxTimestamp() <= ctx.getCurrentWatermark()) {
									ctx.registerProcessingTimeTimer(window.maxTimestamp());
									return TriggerResult.FIRE;
								}
								return TriggerResult.CONTINUE;
							}

							@Override
							public TriggerResult onProcessingTime(long time, TimeWindow window, TriggerContext ctx) throws Exception {
								System.out.println("onProcessingTime:" + time + "now is " + System.currentTimeMillis());
								return TriggerResult.FIRE;
							}

							@Override
							public TriggerResult onEventTime(long time, TimeWindow window, TriggerContext ctx) throws Exception {
								System.out.println("onEventTime:" + time);
								return TriggerResult.CONTINUE;
							}

							@Override
							public void clear(TimeWindow window, TriggerContext ctx) throws Exception {

							}
						})
						.process(new ProcessWindowFunction<AntInputDto, AntInputDto, String, TimeWindow>() {
							@Override
							public void process(String s, Context context, Iterable<AntInputDto> elements, Collector<AntInputDto> out) throws Exception {
								HashMap<String, AntInputDto> cache = Maps.newHashMap();
								System.out.println("process " + System.currentTimeMillis() + ";" +JSON.toJSONString(elements));
								for (AntInputDto element : elements) {
									//只比较30秒钟中前10
									if (cache.containsKey(element.getMatchKey())) {
										cache.remove(element.getMatchKey());
									} else {
										cache.put(element.getMatchKey(), element);
									}
								}

								for (AntInputDto value : cache.values()) {
									long time = (context.window().getEnd() - context.window().getStart()) / 3;
									if (value.getProcessTime() > context.window().getStart() + time) {
										continue;
									}
									out.collect(value);
								}

							}
						}).setParallelism(1);

		sum.print().setParallelism(1);

//		DataStream<Tuple2<String, Integer>> windowCounts = streamSource
//				.flatMap(new FlatMapFunction<String, Tuple2<String, Integer>>() {
//					@Override
//					public void flatMap(String value, Collector<Tuple2<String, Integer>> out) {
//						for (String word : value.split("\\s")) {
//							out.collect(Tuple2.of(word, 1));
//						}
//					}
//				})
//				.keyBy(0)
//				.timeWindow(Time.seconds(5))
//				.sum(1);
//
//		// 将结果打印到控制台，注意这里使用的是单线程打印，而非多线程
//		windowCounts.print().setParallelism(1);

		env.execute("Flink Streaming Java API Skeleton");
	}

}
