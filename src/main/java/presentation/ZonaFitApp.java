package presentation;

import data.ClientDAO;
import data.IClientDAO;
import zona_fit.domain.ClientFit;

import java.util.List;
import java.util.Scanner;

public class ZonaFitApp {
    public static void main(String[] args) {
        System.out.println("**** Welcome to App Zona Fit ****\n");
        fitZoneApp();
        System.out.println("ZonaAppFit finalizado.");
    }

    public static void fitZoneApp(){
        var scanner = new Scanner(System.in);
        boolean result;
        IClientDAO clientDao = new ClientDAO();
        boolean leave = false;

        while (!leave){
            System.out.println("""
              \nMenu App ZonaFit select to option:
                    1. Show users
                    2. Find user
                    3. Add user
                    4. Update user
                    5. Delete user
                    6. Exit
               \s""");

            int option = Integer.parseInt(scanner.next());

            switch (option){
                case 1 -> {
                    List<ClientFit> listUser = clientDao.listClients();
                    listUser.forEach(System.out::println);
                }
                case 2 -> {
                    System.out.println("Enter id's user to find: ");
                    int userId = Integer.parseInt(scanner.next());
                    ClientFit client = new ClientFit(userId);
                    result = clientDao.searchClientById(client);

                    if (result) System.out.println("User found: " + client);
                }
                case 3 -> {
                    System.out.println("Enter the username: ");
                    String name = scanner.next();
                    System.out.println("Enter the lastname: ");
                    String lastname = scanner.next();
                    System.out.println("Enter the suscription: ");
                    int suscription = Integer.parseInt(scanner.next());
                    ClientFit client = new ClientFit(name, lastname, suscription);
                    result = clientDao.addClient(client);

                    if (result) System.out.println("User added success.");
                }
                case 4 -> {
                    System.out.println("Enter the id's user: ");
                    int userId = Integer.parseInt(scanner.next());
                    System.out.println("Enter the update username: ");
                    String name = scanner.next();
                    System.out.println("Enter the update lastname: ");
                    String lastname = scanner.next();
                    System.out.println("Enter the update suscription: ");
                    int suscription = Integer.parseInt(scanner.next());
                    ClientFit client = new ClientFit(userId, name, lastname, suscription);
                    result = clientDao.updateClient(client);

                    if (result) System.out.println("User updated success.");
                }
                case 5 -> {
                    System.out.println("Enter id's user to delete: ");
                    int idUser = Integer.parseInt(scanner.next());
                    ClientFit client = new ClientFit(idUser);
                    result = clientDao.deleteClient(client);

                    if (result) System.out.println("User deleted success.");
                }
                case 6 -> leave = true;
            }

            System.out.println("\nDo you want to select another option? Yes/No");
            String question = scanner.next().toLowerCase();
            if (question.equals("no")) {
                leave = true;
            }

        }
    }
}
