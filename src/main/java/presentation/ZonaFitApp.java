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
        IClientDAO clientDao = new ClientDAO();
        boolean leave = false;

        while (!leave){
            try{
                int selectOption = showMenu(scanner);
                leave = getOptions(scanner, selectOption, clientDao);
            }catch (Exception e){
                System.out.println("Error to execute options: " + e.getMessage());
            }

            if (!leave){
                String question = "";
                boolean cont = true;
                while (cont){
                    System.out.println("\nDo you want to select another option? Yes/No");
                    question = scanner.next().toLowerCase();
                    if (question.equals("yes") || question.equals("no")) {
                        cont = false;
                        if (question.equals("no")){
                            leave = true;
                        }
                    }else {
                        System.out.println("Incorrect Option. Select to correct option (Yes/No)\n");
                    }
                }
            }
        }
    }

    public static int showMenu(Scanner scanner){
        System.out.println("""
              \nMenu App ZonaFit select to option number:
                    1. Show users
                    2. Find user
                    3. Add user
                    4. Update user
                    5. Delete user
                    6. Exit
               \s""");
        return Integer.parseInt(scanner.next());
    }

    public static boolean getOptions(Scanner scanner, int selectOption, IClientDAO clientDao){
        boolean result;
        if (selectOption <= 0 || selectOption >= 7){
            System.out.println("Option number no exist in the menu...");
        }else {
            switch (selectOption){
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
                case 6 -> {
                    return true;
                }
            }
        }
        return false;
    }
}
