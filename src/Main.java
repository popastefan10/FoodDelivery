import menu.Menu;
import menu.Navigation;
import models.Customer;
import models.Driver;
import models.Product;
import models.Restaurant;
import services.CustomerService;
import services.DriverService;
import services.ProductService;
import services.RestaurantService;
import utils.IOUtils;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Navigation navigation = Navigation.getInstance(in);
        navigation.registerDefaultMenus();
        navigation.run();
    }
}