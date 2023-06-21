package jsonplaceholder;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        Utils utils = new Utils();

        //Task 1
        System.out.println("//Task 1");
        System.out.println("--------------------------------------------------------------------------");
        System.out.println(utils.createUser());
        System.out.println("--------------------------------------------------------------------------");
        System.out.println(utils.updateUser(1));
        System.out.println("--------------------------------------------------------------------------");
        System.out.println(utils.deleteUser(5));
        System.out.println("--------------------------------------------------------------------------");
        System.out.println(utils.getAllUser());
        System.out.println("--------------------------------------------------------------------------");
        System.out.println(utils.getUserById(4));
        System.out.println("--------------------------------------------------------------------------");
        System.out.println(utils.getUserByUsername("Samantha"));
        System.out.println();

        //Task 2
        System.out.println("//Task 2");
        System.out.println("--------------------------------------------------------------------------");
        System.out.println(utils.getUserCommentsById(3));
        System.out.println();

        //Task 3
        System.out.println("//Task 3");
        System.out.println("--------------------------------------------------------------------------");
        System.out.println(utils.getUserTodosById(7));

    }
}