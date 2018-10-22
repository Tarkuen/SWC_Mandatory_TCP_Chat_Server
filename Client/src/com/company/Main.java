package com.company;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("**** J_SYSTEM: Client Started" + '\n' + "To connect: " + '\n' + " JOIN <<USERNAME>> , <<IP>>:<<PORT>> ");
        Scanner scn = new Scanner(System.in);
        String joinCommand = scn.nextLine();
        String username = "";
        String[] split;
        String[] split2;
        split = joinCommand.split(" ");

        if (split.length > 3) {
            System.out.println("J_ER 2 : UNWARANTED SPACE");
            System.out.println("**** J_SYSTEM: RESTARTING CLIENT");
            main(args);
        }

        String ip = "";

        int port = 6000;
        if (split[0].equalsIgnoreCase("JOIN")) {
            username = split[1];

            if (username.length() > 12) {
                System.out.println("J_ER 3: BAD SYNTAX: USERNAME LONGER THAN 12 CHARS");
                main(args);
            }

            username = username.replaceAll("[^A-Z||^a-z||^-||^_||^,]", "");
            System.out.println("Accepted Username: " + username);

            if (username.endsWith(",")) {
                username = username.substring(0, username.length() - 1);
            } else {
                System.out.println("J_ER 2 : NO ',' AT END OF USERNAME");
                main(args);
            }
            ip = split[2];

            split2 = ip.split(":");
            ip = split2[0];
            port = Integer.parseInt(split2[1]);
        } else {
            System.out.println("J_ER 2 : BAD SYNTAX - NO COMMAND JOIN FIRST");
            main(args);
        }


        Client client = new Client(username, ip, port);
        main(args);
    }

}
