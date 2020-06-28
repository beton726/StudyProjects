package yusov.extendsExample;

public class Source {
    public static void main(String[] args) {
        Employee employee = new Employee("Tom", "I-company");
        employee.printName();
        employee.work();
        Person sam = new Employee("Sam","Oracle");
        sam.printName();
        sam.getName();
    }
}
