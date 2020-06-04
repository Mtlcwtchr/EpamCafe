package by.epam.mtlcwtchr.ecafe;

import by.epam.mtlcwtchr.ecafe.service.authentication.hash.PasswordHashService;

public class Main {
    public static void main(String[] args) {
        System.out.println(PasswordHashService.hash("S*87&1!13jba36"));
    }
}
