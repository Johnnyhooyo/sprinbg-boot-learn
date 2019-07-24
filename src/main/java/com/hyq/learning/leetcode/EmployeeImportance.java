package com.hyq.learning.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * @author dibulidohu
 * @classname EmployeeImportance
 * @date 2019/6/517:36
 * @description
 */
public class EmployeeImportance {

    public static void main(String[] args) {
        Employee employee = new Employee();
        employee.id = 1;
        employee.importance = 2;
        List<Integer> list = new ArrayList<>();
        list.add(2);
        employee.subordinates = list;

        Employee employee1 = new Employee();
        employee1.id = 2;
        employee1.importance = 3;
        list = new ArrayList<>();
        employee1.subordinates = list;
        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        employees.add(employee1);
        getImportance(employees, 1);
    }

    public static int getImportance(List<Employee> employees, int id) {
        HashMap<Integer, Employee> map = new HashMap<>();
        for(Employee e : employees) {
            map.put(e.id, e);
        }
        int res = 0;
        Stack<Employee> stack = new Stack<>();
        Employee head = map.get(id);
        Employee last = null;
        while(head!= null || !stack.isEmpty()) {
            if(head != null) {
                stack.push(head);
                last = head;
                List<Integer> list = head.subordinates;
                if(list != null && list.size() > 0) {
                    if(!list.contains(last.id)) {
                        head = map.get(list.get(0));
                    } else {
                        int index = list.indexOf(last.id);
                        if (index < list.size() - 1) {
                            head = map.get(list.get(index + 1));
                        } else {
                            head = null;
                        }
                    }
                } else {
                    head = null;
                }
            } else {
                Employee peek = stack.peek();
                if (peek.subordinates != null && !peek.subordinates.isEmpty()) {
                    if(!peek.subordinates.contains(last.id)) {
                        head = map.get(peek.subordinates.get(0));
                    } else {
                        int index = peek.subordinates.indexOf(last.id);
                        if (index < peek.subordinates.size() - 1) {
                            head = map.get(peek.subordinates.get(index + 1));
                        } else {
                            Employee e = stack.pop();
                            res += e.importance;
                            last = e;
                        }
                    }
                } else {
                    Employee e = stack.pop();
                    res += e.importance;
                }
            }
        }
        return res;
    }

    static class Employee{
        private int id;
        private int importance;
        private List<Integer> subordinates;
    }
}
