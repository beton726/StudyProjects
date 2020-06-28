package yusov.abstractExample.firstExample;

public class Source{
    public static void main(String[] args) {
        Employee employee = new Employee("Sam","Peter");
        employee.display();
        Client client = new Client("Anna","Gretta");
        client.display();
    }
}