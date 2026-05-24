package javaproject;

import java.awt.Desktop;
import java.net.URI;
import java.time.LocalDate;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    FlowPane productsPane = new FlowPane();
    ScrollPane scroll = new ScrollPane(productsPane);
    VBox cartItemsBox = new VBox(15);

    ShoppingCart cart = new ShoppingCart(50);

    // ================= CLOTHING OBJECTS =================
    Clothing c1 = new Clothing(
            new String[]{"S", "M", "L", "Xl"}, new String[]{"GREEN", "BLACK"},
            50.0, "Nike", 201, "T-Shirt", 299.99,
            new FlatDiscount(50), "Comfortable cotton t-shirt", 20, 4, 96);

    Clothing c2 = new Clothing(
            new String[]{"S", "M", "L"}, new String[]{"RED", "BLUE"},
            80.0, "Levi's", 202, "Jeans", 799.99,
            new PercentageDiscount(10), "Classic blue denim jeans", 15, 3, 60);

    // ================= LAPTOP OBJECTS =================
    Laptops laptop1 = new Laptops(
            20, "Dell", 1, "XPS 15", 1500,
            new FlatDiscount(10),
            "Intel i7 processor, 2 year warranty. Perfect for work and gaming.",
            10, 5, 170,
            new String[]{"8GB", "16GB", "32GB"},
            new String[]{"128GB", "256GB", "512GB", "1T"});

    Laptops laptop2 = new Laptops(
            25, "Apple", 2, "MacBook Pro", 2500,
            new PercentageDiscount(10),
            "Apple M3 processor, 2 year warranty. Best for creative professionals.",
            5, 4, 80,
            new String[]{"4", "8GB", "16GB"},
            new String[]{"256GB", "512GB", "1T", "2T"});

    // ================= PHONE OBJECTS =================
    Phones phone1 = new Phones(
            new String[]{"128GB", "256GB", "512GB"}, new String[]{"PINK", "BLACK"},
            10, "Samsung", 101, "Galaxy S24", 1200,
            new FlatDiscount(10),
            "108MP camera, 5000mAh battery, 6.7\" AMOLED display, 2 year warranty.",
            20, 4, 55);

    Phones phone2 = new Phones(
            new String[]{"64GB", "128GB", "256GB"}, new String[]{"RED", "BLUE"},
            12, "Apple", 102, "iPhone 15", 1300,
            null,
            "48MP camera, 3877mAh battery, 6.1\" Super Retina XDR display, 2 year warranty.",
            12, 5, 120);

    // ================= SOFTWARE LICENSE OBJECTS =================
    SoftwareLicense s1 = new SoftwareLicense(
            "ABC123-XYZ789", LocalDate.of(2027, 5, 14), 2.5,
            "https://download.com/software1", 301, "Windows Antivirus", 49.99,
            null, "Premium antivirus software", 100, 4, 110);

    SoftwareLicense s2 = new SoftwareLicense(
            "QWE456-RTY111", LocalDate.of(2026, 5, 17), 1.2,
            "https://download.com/software2", 302, "Photo Editor Pro", 79.99,
            null, "Professional photo editing software", 50, 3, 130);

    // ================= DIGITAL DOWNLOAD OBJECTS =================
    DigitalDownload d1 = new DigitalDownload(
            "MP3", 5, 120.5, "https://download.com/music_album",
            401, "Top Hits Album", 19.99,
            null, " 320kbps quality, Instant access after purchase.", 200, 4, 150);

    DigitalDownload d2 = new DigitalDownload(
            "PDF", 3, 15.2, "https://download.com/java_book",
            402, "Java Book", 29.99,
            null, "Complete Java learning ebook", 80, 5, 100);

    // =========================================================
    @Override
    public void start(Stage stage) {
        // Structure
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #f5f7fa;");

        // ================= HEADER =================
        HBox header = new HBox();
        header.prefHeightProperty().bind(root.heightProperty().multiply(0.07));
        header.setMinHeight(40);
        header.maxHeightProperty().bind(root.heightProperty().multiply(0.07));

        Label title = new Label("E-Commerce Store");
        title.setStyle("""
            -fx-font-size: 32px;
            -fx-font-weight: bold;
            -fx-text-fill: #1f2937;
        """);

        header.getChildren().add(title);
        header.setAlignment(Pos.CENTER);
        header.setStyle("""
            -fx-background-color: white;
            -fx-border-color: #dcdfe6;
            -fx-border-width: 0 0 1 0;
            -fx-padding: 25px;
        """);
        root.setTop(header);

        

        // ================= LEFT SIDEBAR =================
        VBox sidebar = new VBox(25);
        sidebar.setPadding(new Insets(25));
        sidebar.prefWidthProperty().bind(root.widthProperty().multiply(0.17));
        sidebar.setMinWidth(225);
        sidebar.setStyle("""
            -fx-background-color: #e9eef5;
            -fx-spacing: 15px;
            -fx-border-color: #d0d7e2;
            -fx-border-width: 0 1 0 0;
        """);

        //logo
        Label logo = new Label("E-Commerce\nStore");
        logo.setFont(Font.font("Arial", 28));
        logo.setStyle("-fx-font-weight: bold; -fx-text-fill: #2563eb;");

        //search 
        TextField searchField = new TextField();
        searchField.setPromptText("Search Product...");
        searchField.setPrefHeight(40);
        searchField.setStyle("""
            -fx-background-radius: 10;
            -fx-font-size: 14px;
            -fx-padding: 8;
        """);
        searchField.textProperty().addListener((obs, oldVal, newVal) -> searchProducts(newVal));

        //btns
        Button homeBtn    = new Button("🏠 Home");
        Button clothesBtn = new Button("👕 Clothing");
        Button laptopsBtn = new Button("💻 Laptops");
        Button phonesBtn  = new Button("📱 Phones");
        Button digitalBtn = new Button("🎮 Digital Products");
        Button clearCartBtn = new Button("🗑 Clear Cart");
                
        Button[] menuButtons = {
            homeBtn,
            clothesBtn,
            laptopsBtn,
            phonesBtn,
            digitalBtn
        };

        // styles
        String menuStyle = """
            -fx-background-color: transparent;
            -fx-text-fill: #1f2937;
            -fx-font-size: 16px;
            -fx-font-weight: 600;
            -fx-background-radius: 10;
            -fx-padding: 14 18 14 18;
            -fx-alignment: CENTER-LEFT;
            -fx-cursor: hand;
        """;
        String hoverStyle = """
            -fx-background-color: #dbeafe;
            -fx-text-fill: #2563eb;
            -fx-font-size: 16px;
            -fx-font-weight: bold;
            -fx-background-radius: 10;
            -fx-padding: 14 18 14 18;
            -fx-alignment: CENTER-LEFT;
            -fx-cursor: hand;
        """;
        String clearCartDefault = """
            -fx-background-color: white;
            -fx-text-fill: #111827;
            -fx-font-size: 15px;
            -fx-font-weight: bold;
            -fx-background-radius: 8;
            -fx-border-color: #d1d5db;
            -fx-border-radius: 8;
            -fx-padding: 12;
            -fx-cursor: hand;
        """;
        String clearCartHover = """
            -fx-background-color: #fee2e2;
            -fx-text-fill: #b91c1c;
            -fx-font-size: 15px;
            -fx-font-weight: bold;
            -fx-background-radius: 8;
            -fx-border-color: #fca5a5;
            -fx-border-radius: 8;
            -fx-padding: 12;
            -fx-cursor: hand;
        """;

        // btns stylesء
        homeBtn.setStyle(menuStyle);
        homeBtn.setMaxWidth(Double.MAX_VALUE);
        clothesBtn.setStyle(menuStyle);
        clothesBtn.setMaxWidth(Double.MAX_VALUE);
        laptopsBtn.setStyle(menuStyle);
        laptopsBtn.setMaxWidth(Double.MAX_VALUE);
        phonesBtn.setStyle(menuStyle);
        phonesBtn.setMaxWidth(Double.MAX_VALUE);
        digitalBtn.setStyle(menuStyle);
        digitalBtn.setMaxWidth(Double.MAX_VALUE);
        clearCartBtn.setStyle(clearCartDefault);
        clearCartBtn.setMaxWidth(Double.MAX_VALUE);
        
        //hover , active
        final Button[] activeBtn = {homeBtn};

        for (Button btn : menuButtons) {
            btn.setStyle(menuStyle);

            btn.setOnMouseEntered(e -> {
                if (btn != activeBtn[0]) {
                    btn.setStyle(hoverStyle);
                }
            });
            btn.setOnMouseExited(e -> {
                if (btn != activeBtn[0]) {
                    btn.setStyle(menuStyle);
                }
            });
        }

        homeBtn.setStyle(hoverStyle);

        //home
        homeBtn.setOnAction(e -> {
            productsPane.getChildren().setAll(
                createProductCard(laptop1), createProductCard(laptop2),
                createProductCard(phone1),  createProductCard(phone2),
                createProductCard(c1),      createProductCard(c2),
                createProductCard(s1),      createProductCard(s2),
                createProductCard(d1),      createProductCard(d2));
            scroll.setContent(productsPane);
            root.setCenter(scroll);
            
            activeBtn[0].setStyle(menuStyle);
            activeBtn[0] = homeBtn;
            homeBtn.setStyle(hoverStyle);
        });

        //clothes
        clothesBtn.setOnAction(e -> {
            productsPane.getChildren().setAll(
                    createProductCard(c1),createProductCard(c2));
            scroll.setContent(productsPane);
            root.setCenter(scroll);
            
            activeBtn[0].setStyle(menuStyle);
            activeBtn[0] = clothesBtn;
            clothesBtn.setStyle(hoverStyle);
        });

        //laptop
        laptopsBtn.setOnAction(e -> {
            productsPane.getChildren().setAll(
                    createProductCard(laptop1),createProductCard(laptop2));
            scroll.setContent(productsPane);
            root.setCenter(scroll);
            
            activeBtn[0].setStyle(menuStyle);
            activeBtn[0] = laptopsBtn;
            laptopsBtn.setStyle(hoverStyle);
        });

        // phone
        phonesBtn.setOnAction(e -> {
            productsPane.getChildren().setAll(
                    createProductCard(phone1),createProductCard(phone2));
            scroll.setContent(productsPane);
            root.setCenter(scroll);
            
            activeBtn[0].setStyle(menuStyle);
            activeBtn[0] = phonesBtn;
            phonesBtn.setStyle(hoverStyle);
        });

        //digital
        digitalBtn.setOnAction(e -> {
            productsPane.getChildren().setAll(
                createProductCard(s1), createProductCard(s2),
                createProductCard(d1), createProductCard(d2));
            scroll.setContent(productsPane);
            root.setCenter(scroll);
            
            activeBtn[0].setStyle(menuStyle);
            activeBtn[0] = digitalBtn;
            digitalBtn.setStyle(hoverStyle);
        });

        //clear
        clearCartBtn.setOnMouseEntered(e -> clearCartBtn.setStyle(clearCartHover));
        clearCartBtn.setOnMouseExited(e -> clearCartBtn.setStyle(clearCartDefault));
        clearCartBtn.setOnAction(e -> {
            cart.clearCart();
            refreshCartUI();
        });

        sidebar.getChildren().addAll(logo, searchField, homeBtn, clothesBtn,
                laptopsBtn, phonesBtn, digitalBtn, clearCartBtn);
        root.setLeft(sidebar);

        
        // ================= RIGHT CART =================
        VBox cartPane = new VBox(20);
        cartPane.setPadding(new Insets(25));
        cartPane.setMinWidth(300);

        Label cartTitle = new Label("🛒 Shopping Cart");
        cartTitle.setFont(Font.font("Arial", 26));
        cartTitle.setStyle("-fx-font-size: 28px;"
                + " -fx-font-weight: bold;"
                + " -fx-text-fill: #111827;");
        cartTitle.setMinHeight(Region.USE_PREF_SIZE);

        Separator line = new Separator();
        line.setPrefHeight(2);
        line.setStyle("-fx-background-color: #e5e7eb;");

        cartItemsBox = new VBox(15);
        cartItemsBox.setAlignment(Pos.TOP_CENTER);
        cartItemsBox.setStyle("""
            -fx-background-color: white;
            -fx-padding: 15;
        """);

        ScrollPane cartScroll = new ScrollPane(cartItemsBox);
        cartScroll.setFitToWidth(true);
        cartScroll.setFitToHeight(true);
        cartScroll.setStyle("""
            -fx-background: white;
            -fx-background-color: white;
            -fx-border-color: #e5e7eb;
            -fx-border-radius: 10;
            -fx-background-radius: 10;
        """);

        refreshCartUI();

        Button checkoutBtn = new Button("Checkout");
        checkoutBtn.setMaxWidth(Double.MAX_VALUE);
        checkoutBtn.setStyle("""
            -fx-background-color: #2fb344;
            -fx-text-fill: white;
            -fx-font-size: 18px;
            -fx-font-weight: bold;
            -fx-background-radius: 8;
            -fx-padding: 14;
        """);
        checkoutBtn.setOnMouseEntered(e -> checkoutBtn.setStyle("""
            -fx-background-color: #24963a;
            -fx-text-fill: white;
            -fx-font-size: 18px;
            -fx-font-weight: bold;
            -fx-background-radius: 8;
            -fx-padding: 14;
            -fx-cursor: hand;
        """));
        checkoutBtn.setOnMouseExited(e -> checkoutBtn.setStyle("""
            -fx-background-color: #2fb344;
            -fx-text-fill: white;
            -fx-font-size: 18px;
            -fx-font-weight: bold;
            -fx-background-radius: 8;
            -fx-padding: 14;
        """));
        
        // =========================================================
        // CHECKOUT BUTTON ACTION
        // =========================================================

        checkoutBtn.setOnAction(e -> {

    Stage checkoutStage = new Stage();
    checkoutStage.setTitle("Checkout");

    VBox rootCheckout = new VBox(18);
    rootCheckout.setAlignment(Pos.CENTER);
    rootCheckout.setPadding(new Insets(10));
    rootCheckout.setStyle("-fx-background-color: #f5f7fa;");

    // STORE NAME 
    Label storeLabel = new Label("🛍 E-Commerce Store");
    storeLabel.setStyle("""
        -fx-font-size: 26px;
        -fx-font-weight: bold;
        -fx-text-fill: #2563eb;
    """);

    Separator sep1 = new Separator();

    // INFO Person =========
    GridPane infoGrid = new GridPane();
    infoGrid.setHgap(15);
    infoGrid.setVgap(12);
    infoGrid.setPadding(new Insets(10, 0, 10, 0));
    infoGrid.setAlignment(Pos.CENTER);

    String labelStyle =
        "-fx-font-size: 14px; " +
        "-fx-font-weight: bold; " +
        "-fx-text-fill: #374151;";

    String fieldStyle =
        "-fx-background-radius: 8; " +
        "-fx-padding: 8; " +
        "-fx-font-size: 14px; " +
        "-fx-border-color: #d1d5db; " +
        "-fx-border-radius: 8;";

    Label nameTitle = new Label("👤 Full Name:");
    nameTitle.setStyle(labelStyle);

    TextField nameField = new TextField();
    nameField.setPromptText("Enter your full name");
    nameField.setPrefWidth(260);
    nameField.setStyle(fieldStyle);

    Label addressTitle = new Label("📍 Address:");
    addressTitle.setStyle(labelStyle);

    TextField addressField = new TextField();
    addressField.setPromptText("Enter your address");
    addressField.setPrefWidth(260);
    addressField.setStyle(fieldStyle);

    Label phoneTitle = new Label("📞 Phone:");
    phoneTitle.setStyle(labelStyle);

    TextField phoneField = new TextField();
    phoneField.setPromptText("Enter your phone number");
    phoneField.setPrefWidth(260);
    phoneField.setStyle(fieldStyle);

    infoGrid.add(nameTitle, 0, 0);
    infoGrid.add(nameField, 1, 0);

    infoGrid.add(addressTitle, 0, 1);
    infoGrid.add(addressField, 1, 1);

    infoGrid.add(phoneTitle, 0, 2);
    infoGrid.add(phoneField, 1, 2);

    Separator sep2 = new Separator();

    //  ORDER SUMMARY ===============
    Label orderTitle = new Label("Order Summary");
    orderTitle.setStyle(""" 
        -fx-font-size: 17px;
        -fx-font-weight: bold;
        -fx-text-fill: #111827;
    """);

    GridPane orderGrid = new GridPane();
    orderGrid.setHgap(25);
    orderGrid.setVgap(10);
    orderGrid.setPadding(new Insets(8, 0, 8, 0));
    orderGrid.setAlignment(Pos.CENTER);

    String headerStyle =
        "-fx-font-size: 13px; " +
        "-fx-font-weight: bold; " +
        "-fx-text-fill: #6b7280;";

    Label hStore = new Label("Store");
    hStore.setStyle(headerStyle);

    Label hProduct = new Label("Product");
    hProduct.setStyle(headerStyle);

    Label hQty = new Label("Qty");
    hQty.setStyle(headerStyle);

    Label hPrice = new Label("Unit Price");
    hPrice.setStyle(headerStyle);

    Label hSubtotal = new Label("Subtotal");
    hSubtotal.setStyle(headerStyle);

    orderGrid.add(hStore, 0, 0);
    orderGrid.add(hProduct, 1, 0);
    orderGrid.add(hQty, 2, 0);
    orderGrid.add(hPrice, 3, 0);
    orderGrid.add(hSubtotal, 4, 0);

    double totalPrice = 0;

    for (int i = 0; i < cart.size; i++) {

        CartItem item = cart.items[i];
        Product prod = item.product;

        String cellStyle =
            "-fx-font-size: 13px; " +
            "-fx-text-fill: #111827;";

        String storeName = "Digital";

        if (prod instanceof PhysicalProduct pp) {
            storeName = pp.brand;
        }

        Label pStore = new Label(storeName);
        pStore.setStyle(cellStyle);

        Label pName = new Label(prod.name);
        pName.setStyle(cellStyle);

        Label pQty = new Label(String.valueOf(item.quantity));
        pQty.setStyle(cellStyle);

        Label pPrice = new Label(
            String.format("$%.2f", prod.getFinalPrice())
        );
        pPrice.setStyle(cellStyle);

        Label pSub = new Label(
            String.format("$%.2f", item.getSubTotal())
        );
        pSub.setStyle("""
            -fx-font-size: 13px;
            -fx-font-weight: bold;
            -fx-text-fill: #2fb344;
        """);

        orderGrid.add(pStore, 0, i + 1);
        orderGrid.add(pName, 1, i + 1);
        orderGrid.add(pQty, 2, i + 1);
        orderGrid.add(pPrice, 3, i + 1);
        orderGrid.add(pSub, 4, i + 1);

        totalPrice += item.getSubTotal();
    }

    Separator sep3 = new Separator();

    //  TOTAL =========
    HBox totalBox = new HBox();
    totalBox.setAlignment(Pos.CENTER_RIGHT);

    Label totalLabel = new Label(
        String.format("Grand Total: $%.2f", totalPrice)
    );

    totalLabel.setStyle("""
        -fx-font-size: 20px;
        -fx-font-weight: bold;
        -fx-text-fill: #16a34a;
    """);

    totalBox.getChildren().add(totalLabel);

    Separator sep4 = new Separator();

    // ================= PAYMENT =================
    Label paymentLabel = new Label("💳 Payment Method — Visa Card");

    paymentLabel.setStyle("""
        -fx-font-size: 16px;
        -fx-font-weight: bold;
        -fx-text-fill: #2563eb;
    """);

    GridPane payGrid = new GridPane();
    payGrid.setHgap(15);
    payGrid.setVgap(12);
    payGrid.setPadding(new Insets(10, 0, 10, 0));
    payGrid.setAlignment(Pos.CENTER);

    Label visaTitle = new Label("💳 Visa Number:");
    visaTitle.setStyle(labelStyle);

    TextField visaField = new TextField();
    visaField.setPromptText("14 digits");
    visaField.setPrefWidth(260);
    visaField.setStyle(fieldStyle);

    Label passTitle = new Label("🔒 Password:");
    passTitle.setStyle(labelStyle);

    PasswordField passField = new PasswordField();
    passField.setPromptText("4 digits");
    passField.setPrefWidth(260);
    passField.setStyle(fieldStyle);

    payGrid.add(visaTitle, 0, 0);
    payGrid.add(visaField, 1, 0);

    payGrid.add(passTitle, 0, 1);
    payGrid.add(passField, 1, 1);

    //  RESULT 
    Label result = new Label();

    // PAY BUTTON ===========
    Button payBtn = new Button("Pay Now 💳");

    payBtn.setPrefWidth(220);

    payBtn.setStyle("""
        -fx-background-color: #2563eb;
        -fx-text-fill: white;
        -fx-font-size: 16px;
        -fx-font-weight: bold;
        -fx-background-radius: 10;
        -fx-cursor: hand;
        -fx-padding: 12 30 12 30;
    """);

    payBtn.setOnMouseEntered(ev ->
        payBtn.setStyle("""
            -fx-background-color: #1d4ed8;
            -fx-text-fill: white;
            -fx-font-size: 16px;
            -fx-font-weight: bold;
            -fx-background-radius: 10;
            -fx-cursor: hand;
            -fx-padding: 12 30 12 30;
        """)
    );

    payBtn.setOnMouseExited(ev ->
        payBtn.setStyle("""
            -fx-background-color: #2563eb;
            -fx-text-fill: white;
            -fx-font-size: 16px;
            -fx-font-weight: bold;
            -fx-background-radius: 10;
            -fx-cursor: hand;
            -fx-padding: 12 30 12 30;
        """)
    );

    //  PAY ACTION 
    payBtn.setOnAction(ev -> {

        String name = nameField.getText();
        String address = addressField.getText();
        String phone = phoneField.getText();

        String visa = visaField.getText();
        String pass = passField.getText();

        if (cart.isEmpty()) {

            result.setText("Cart is Empty ❌");

            result.setStyle("""
                -fx-text-fill: #dc2626;
                -fx-font-size: 14px;
                -fx-font-weight: bold;
            """);

            return;
        }

        if (name.isEmpty() ||
            address.isEmpty() ||
            phone.isEmpty()) {

            result.setText(
                "Please fill Name, Address and Phone ❌"
            );

            result.setStyle("""
                -fx-text-fill: #dc2626;
                -fx-font-size: 14px;
                -fx-font-weight: bold;
            """);

            return;
        }

        if (visa.matches("\\d{14}") &&
            pass.matches("\\d{4}")) {

            checkoutStage.close();

            cart.clearCart();
            refreshCartUI();

            // SUCCESS WINDOW =================
            Stage successStage = new Stage();

            VBox successBox = new VBox(20);
            successBox.setAlignment(Pos.CENTER);
            successBox.setPadding(new Insets(40));

            successBox.setStyle("""
                -fx-background-color: #f0fdf4;
            """);

            Label tick = new Label("✅");
            tick.setFont(Font.font(60));

            Label msg = new Label(
                "Order Placed Successfully!"
            );

            msg.setStyle("""
                -fx-font-size: 22px;
                -fx-font-weight: bold;
                -fx-text-fill: #15803d;
            """);

            Label sub = new Label(
                "Thank you, " + name + "!\n" +
                "📍 Deliver to: " + address + "\n" +
                "📞 Phone: " + phone
            );

            sub.setStyle("""
                -fx-font-size: 14px;
                -fx-text-fill: #374151;
            """);

            sub.setTextAlignment(TextAlignment.CENTER);

            Button closeBtn = new Button("Close");

            closeBtn.setStyle("""
                -fx-background-color: #2563eb;
                -fx-text-fill: white;
                -fx-font-size: 14px;
                -fx-font-weight: bold;
                -fx-background-radius: 8;
                -fx-padding: 10 30 10 30;
                -fx-cursor: hand;
            """);

            closeBtn.setOnAction(event ->
                successStage.close()
            );

            successBox.getChildren().addAll(
                tick,
                msg,
                sub,
                closeBtn
            );

            successStage.setScene(
                new Scene(successBox, 400, 300)
            );

            successStage.setResizable(false);
            successStage.show();

        } else {

            result.setText(
                "Invalid Visa Number or Password ❌"
            );

            result.setStyle("""
                -fx-text-fill: #dc2626;
                -fx-font-size: 14px;
                -fx-font-weight: bold;
            """);
        }
    });

    // ================= ADD ALL =================
    rootCheckout.getChildren().addAll(
        storeLabel,
        sep1,
        infoGrid,
        sep2,
        orderTitle,
        orderGrid,
        sep3,
        totalBox,
        sep4,
        paymentLabel,
        payGrid,
        payBtn,
        result
    );

    ScrollPane sp = new ScrollPane(rootCheckout);

    sp.setFitToWidth(true);

    sp.setStyle("""
        -fx-background-color: #f5f7fa;
        -fx-background: #f5f7fa;
    """);

    checkoutStage.setScene(
        new Scene(sp, 480, 680)
    );

    checkoutStage.setResizable(false);
    checkoutStage.show();
});

        cartPane.getChildren().addAll(cartTitle, line, cartScroll, checkoutBtn);
        root.setRight(cartPane);

        // ================= CENTER PRODUCTS =================
        productsPane.setPadding(new Insets(20));
        productsPane.setHgap(20);
        productsPane.setVgap(20);
        productsPane.setAlignment(Pos.TOP_CENTER);
        productsPane.setStyle("-fx-background-color: #fff;");

        productsPane.getChildren().addAll(
            createProductCard(c1),      createProductCard(c2),
            createProductCard(laptop1), createProductCard(laptop2),
            createProductCard(phone1),  createProductCard(phone2),
            createProductCard(s1),      createProductCard(s2),
            createProductCard(d1),      createProductCard(d2));

        scroll = new ScrollPane(productsPane);
        scroll.setFitToWidth(true);
        scroll.setStyle("""
            -fx-background: #f5f7fa;
            -fx-background-color: #f5f7fa;
            -fx-border-color: transparent;
        """);
        scroll.prefWidthProperty().bind(
            root.widthProperty()
                .subtract(sidebar.widthProperty())
                .subtract(cartPane.widthProperty()));
        root.setCenter(scroll);

        Scene scene = new Scene(root);
        stage.setTitle("E-Commerce Product Store");
        stage.setMinHeight(660);
        stage.setMinWidth(780);
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    // =========================================================
    // refreshCartUI
    // =========================================================
    private void refreshCartUI() {
        cartItemsBox.getChildren().clear();

        if (cart.isEmpty()) {
            Label emptyIcon = new Label("🛒");
            emptyIcon.setFont(Font.font(60));
            emptyIcon.setTextFill(Color.web("#9ca3af"));
            Label emptyMsg = new Label("Your cart is empty");
            emptyMsg.setFont(Font.font(18));
            emptyMsg.setTextFill(Color.web("#6b7280"));
            cartItemsBox.getChildren().addAll(emptyIcon, emptyMsg);
            return;
        }

        double grandTotal = 0;

        for (int i = 0; i < cart.size; i++) {
            CartItem item = cart.items[i];
            Product  prod = item.product;

            VBox itemCard = new VBox(5);
            itemCard.setPadding(new Insets(8));
            itemCard.setStyle("""
                -fx-background-color: #f9fafb;
                -fx-background-radius: 8;
                -fx-border-color: #e5e7eb;
                -fx-border-radius: 8;
            """);

            Label nameL = new Label(prod.name);
            nameL.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #111827;");
            nameL.setWrapText(true);
            itemCard.getChildren().add(nameL);

            Label priceL = new Label(String.format("$%.2f / unit", prod.getFinalPrice()));
            priceL.setStyle("-fx-font-size: 11px; -fx-text-fill: #6b7280;");
            itemCard.getChildren().add(priceL);

            HBox qtyRow = new HBox(6);
            qtyRow.setAlignment(Pos.CENTER_LEFT);

            Button minusBtn = new Button("−");
            Button plusBtn  = new Button("+");
            String qBtnStyle = """
                -fx-background-color: #e5e7eb;
                -fx-font-size: 15px;
                -fx-font-weight: bold;
                -fx-background-radius: 6;
                -fx-min-width: 26; -fx-min-height: 26;
                -fx-cursor: hand;
            """;
            minusBtn.setStyle(qBtnStyle);
            plusBtn.setStyle(qBtnStyle);

            Label qtyL = new Label(String.valueOf(item.quantity));
            qtyL.setStyle("-fx-font-size: 14px;"
                    + " -fx-font-weight: bold;"
                    + " -fx-min-width: 22;"
                    + " -fx-alignment: CENTER;");

            Label subL = new Label(String.format("$%.2f", item.getSubTotal()));
            subL.setStyle("-fx-font-size: 12px;"
                    + " -fx-font-weight: bold;"
                    + " -fx-text-fill: #2fb344;");

            

            final int pid = prod.id;

            minusBtn.setOnAction(e -> {
                if (item.quantity > 1) item.decreaseQuantity();
                else cart.removeProduct(pid);
                refreshCartUI();
            });
            plusBtn.setOnAction(e -> {
                item.increaseQuantity();
                refreshCartUI();
            });
            

            qtyRow.getChildren().addAll(minusBtn, qtyL, plusBtn, subL);
            itemCard.getChildren().add(qtyRow);
            cartItemsBox.getChildren().add(itemCard);
            grandTotal += item.getSubTotal();
        }

        Separator sep = new Separator();
        sep.setStyle("-fx-background-color: #e5e7eb;");
        HBox totalRow = new HBox();
        totalRow.setAlignment(Pos.CENTER_RIGHT);
        Label totalL = new Label(String.format("Total:  $%.2f", grandTotal));
        totalL.setFont(Font.font("Arial", 15));
        totalL.setStyle("-fx-font-weight: bold; -fx-text-fill: #111827;");
        totalRow.getChildren().add(totalL);
        cartItemsBox.getChildren().addAll(sep, totalRow);
    }

    // =========================================================
    // getProductImageUrl
    // =========================================================
    private String getProductImageUrl(Product p) {
        if (p instanceof Laptops) {
            if (p.name.toLowerCase().contains("mac"))
                return "https://images.unsplash.com/photo-1517336714731-489689fd1ca8?w=200&h=140&fit=crop";
            return "https://images.unsplash.com/photo-1593642632559-0c6d3fc62b89?w=200&h=140&fit=crop";
        } else if (p instanceof Phones) {
            if (p.name.toLowerCase().contains("iphone"))
                return "https://images.unsplash.com/photo-1511707171634-5f897ff02aa9?w=200&h=140&fit=crop";
            return "https://images.unsplash.com/photo-1610945265064-0e34e5519bbf?w=200&h=140&fit=crop";
        } else if (p instanceof Clothing) {
            if (p.name.toLowerCase().contains("jeans"))
                return "https://images.unsplash.com/photo-1542272604-787c3835535d?w=200&h=140&fit=crop";
            return "https://images.unsplash.com/photo-1521572163474-6864f9cf17ab?w=200&h=140&fit=crop";
        } else if (p instanceof SoftwareLicense) {
            if (p.name.toLowerCase().contains("photo"))
                return "https://images.unsplash.com/photo-1611532736597-de2d4265fba3?w=200&h=140&fit=crop";
            return "https://images.unsplash.com/photo-1558494949-ef010cbdcc31?w=200&h=140&fit=crop";
        } else if (p instanceof DigitalDownload) {
            if (p.name.toLowerCase().contains("music") || p.name.toLowerCase().contains("album"))
                return "https://images.unsplash.com/photo-1470225620780-dba8ba36b745?w=200&h=140&fit=crop";
            return "https://images.unsplash.com/photo-1544716278-ca5e3f4abd8c?w=200&h=140&fit=crop";
        }
        return "https://images.unsplash.com/photo-1523275335684-37898b6baf30?w=200&h=140&fit=crop";
    }

    // =========================================================
    // createProductCard
    // =========================================================
    private VBox createProductCard(Product p) {

        VBox card = new VBox(10);
        card.setPadding(new Insets(15));
        card.setPrefWidth(180);
        card.setAlignment(Pos.CENTER);
        card.setStyle("""
            -fx-background-color: white;
            -fx-background-radius: 12;
            -fx-border-color: #e5e7eb;
            -fx-border-radius: 12;
            -fx-padding: 15;
            -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.08), 10, 0, 0, 3);
            -fx-cursor: hand;
        """);

        card.setOnMouseEntered(e -> {
            TranslateTransition tt = new TranslateTransition(Duration.millis(150), card);
            tt.setToY(-5);
            tt.play();
        });
        card.setOnMouseExited(e -> {
            TranslateTransition tt = new TranslateTransition(Duration.millis(150), card);
            tt.setToY(0);
            tt.play();
        });

        //image
        ImageView imageView = new ImageView();
        imageView.setFitWidth(170);
        imageView.setFitHeight(120);
        imageView.setPreserveRatio(false);
        imageView.setStyle("-fx-background-radius: 8;");
        try {
            Image img = new Image(getProductImageUrl(p), 200, 140, false, true, true);
            imageView.setImage(img);
        } catch (Exception ex) { }

        //name
        Label nameLabel = new Label(p.name);
        nameLabel.setStyle("-fx-font-size: 19px; -fx-font-weight: bold; -fx-text-fill: #111827;");

        //price
        Label priceLabel = new Label(String.format("$%.2f", p.getFinalPrice()));
        priceLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #111827;");

        //add to cart
        Button addBtn = new Button("Add To Cart");
        addBtn.setMaxWidth(Double.MAX_VALUE);
        addBtn.setStyle("""
            -fx-background-color: #edf2f7;
            -fx-text-fill: #2563eb;
            -fx-font-size: 15px;
            -fx-font-weight: bold;
            -fx-background-radius: 8;
            -fx-padding: 10 18 10 18;
            -fx-cursor: hand;
        """);
        addBtn.setOnMouseEntered(e -> addBtn.setStyle("""
            -fx-background-color: #dbeafe;
            -fx-text-fill: #1d4ed8;
            -fx-font-size: 15px;
            -fx-font-weight: bold;
            -fx-background-radius: 8;
            -fx-padding: 10 18 10 18;
            -fx-cursor: hand;
        """));
        addBtn.setOnMouseExited(e -> addBtn.setStyle("""
            -fx-background-color: #edf2f7;
            -fx-text-fill: #2563eb;
            -fx-font-size: 15px;
            -fx-font-weight: bold;
            -fx-background-radius: 8;
            -fx-padding: 10 18 10 18;
        """));

        addBtn.setOnAction(e -> {
            e.consume();
            openDetailWindow(p);
        });

        card.setOnMouseClicked(e -> openDetailWindow(p));

        card.getChildren().addAll(imageView, nameLabel, priceLabel, addBtn);
        return card;
    }

    // =========================================================
    // openDetailWindow
    // =========================================================
    private void openDetailWindow(Product p) {

        Stage detailStage = new Stage();
        detailStage.setTitle(p.name + " - Details");

        VBox content = new VBox(20);
        content.setPadding(new Insets(40));
        content.setAlignment(Pos.CENTER);
        content.setStyle("-fx-background-color: #f5f7fa;");

        //image
        ImageView imageViewIn = new ImageView();
        imageViewIn.setFitWidth(420);
        imageViewIn.setFitHeight(300);
        imageViewIn.setPreserveRatio(false);
        try {
            Image img = new Image(getProductImageUrl(p), 200, 140, false, true, true);
            imageViewIn.setImage(img);
        } catch (Exception ex) { }
        content.getChildren().add(imageViewIn);

        //name
        Label detailName = new Label(p.name);
        detailName.setFont(Font.font("Arial", 28));
        detailName.setStyle("-fx-font-weight: bold; -fx-text-fill: #111827;");
        detailName.setMaxWidth(320);
        detailName.setTextAlignment(TextAlignment.LEFT);

        //rating
        HBox rateBox = new HBox(10);
        HBox stars = new HBox(3);
        for (int i = 1; i <= 5; i++) {
            Label star = new Label("⭐");
            star.setStyle("-fx-font-size: 30px; -fx-padding: -15 0 -25 0;");
            star.setTextFill(Color.web("#FFAD33"));
            if (i > p.rating) star.setOpacity(0.6);
            stars.getChildren().add(star);
        }
        Label reviews = new Label("(" + p.reviews + " reviews)");
        reviews.setFont(Font.font(16));
        reviews.setTextFill(Color.web("#aaa"));
        rateBox.getChildren().addAll(stars, reviews);
        rateBox.setAlignment(Pos.CENTER_LEFT);

        //description
        Label detailDesc = new Label(p.description);
        detailDesc.setTextFill(Color.web("#6b7280"));
        detailDesc.setFont(Font.font(15));
        detailDesc.setWrapText(true);
        detailDesc.setMaxWidth(320);
        detailDesc.setTextAlignment(TextAlignment.LEFT);

        //price
        HBox HBoxPrice = new HBox(20);
        Label detailPrice = new Label(String.format("$%.2f", p.getFinalPrice()));
        detailPrice.setFont(Font.font("Arial", 32));
        detailPrice.setTextFill(Color.web("#2fb344"));
        detailPrice.setMaxWidth(320);
        detailPrice.setTextAlignment(TextAlignment.LEFT);

        double tax      = (p instanceof PhysicalProduct) ? ((PhysicalProduct) p).calculateTax() : 0;
        double shipping = (p instanceof PhysicalProduct) ? ((PhysicalProduct) p).getShippableCost() : 0;
        Text oldPrice = new Text("");
        if (p.discount != null) {
            oldPrice.setText(String.format("$%.2f", (p.price + tax + shipping)));
            oldPrice.setFont(Font.font("Arial", 30));
            oldPrice.setStrikethrough(true);
            oldPrice.setFill(Color.web("#999"));
            oldPrice.setStyle("-fx-strikethrough: true; -fx-text-fill: gray;");
            oldPrice.setTextAlignment(TextAlignment.LEFT);
        }
        HBoxPrice.getChildren().addAll(detailPrice, oldPrice);
        
        //add
        content.getChildren().addAll(detailName, detailDesc, rateBox);

        // variables
        String[] selectedColor   = {""};
        String[] selectedSize    = {""};
        String[] selectedStorage = {""};
        String[] selectedRam     = {""};

        // ── Clothing: colour + size ──
        if (p instanceof Clothing cl) {
            //color
            HBox colorBox = new HBox(15);
            Label colorLabel = new Label("Color : ");
            colorLabel.setFont(Font.font("Arial", 22));
            colorLabel.setTextFill(Color.web("#555"));
            colorBox.getChildren().add(colorLabel);

            String[] colors  = cl.colors;
            Circle[] circles = new Circle[colors.length];
            selectedColor[0] = colors[0];

            for (int i = 0; i < colors.length; i++) {
                Circle c = new Circle(8);
                try { c.setFill(Color.web(colors[i])); }
                catch (Exception ex) { c.setFill(Color.LIGHTGRAY); }
                circles[i] = c;
                if (i == 0) { c.setStroke(Color.web("#555")); c.setStrokeWidth(2); }
                int index = i;
                c.setOnMouseEntered(e -> c.setStyle("-fx-cursor: hand;"));
                c.setOnMouseClicked(e -> {
                    selectedColor[0] = colors[index];
                    for (Circle cr : circles) cr.setStroke(Color.TRANSPARENT);
                    c.setStroke(Color.web("#555")); c.setStrokeWidth(2);
                });
                colorBox.getChildren().add(c);
            }
            colorBox.setAlignment(Pos.CENTER_LEFT);
            content.getChildren().add(colorBox);

            //size
            HBox sizeBox = new HBox(10);
            Label sizeLabel = new Label("Size : ");
            sizeLabel.setFont(Font.font("Arial", 22));
            sizeLabel.setTextFill(Color.web("#555"));
            sizeBox.getChildren().add(sizeLabel);
            
            String[] clothingSizes = cl.sizes;
            selectedSize[0] = clothingSizes[0];
            
            for (String size : clothingSizes) {
                Button sizeBtn = new Button(size);
                sizeBtn.setStyle(size.equals(clothingSizes[0])
                    ? "-fx-background-color: linear-gradient(to right,"
                            + " #00c6ff, #0072ff); -fx-text-fill: white;"
                            + " -fx-font-weight: bold; -fx-cursor: hand;"
                    : "-fx-font-weight: bold;");
                sizeBtn.setOnMouseEntered(e -> {
                    if (!selectedSize[0].equals(size))
                        sizeBtn.setStyle("-fx-font-weight: bold; -fx-cursor: hand;");
                });
                sizeBtn.setOnMouseExited(e -> {
                    if (!selectedSize[0].equals(size))
                        sizeBtn.setStyle("-fx-font-weight: bold;");
                });
                
                sizeBtn.setOnMouseClicked(e -> {
                    selectedSize[0] = size;
                    for (var node : sizeBox.getChildren())
                        if (node instanceof Button btn) btn.setStyle("-fx-font-weight: bold;");
                    sizeBtn.setStyle("-fx-background-color: linear-gradient(to right,"
                            + " #00c6ff, #0072ff); -fx-text-fill: white;"
                            + " -fx-font-weight: bold; -fx-cursor: hand;");
                });
                sizeBox.getChildren().add(sizeBtn);
            }
            content.getChildren().add(sizeBox);
        }

        // ── Phones: color + storage ──
        if (p instanceof Phones ph) {
            //color
            HBox colorBox = new HBox(15);
            Label colorLabel = new Label("Color : ");
            colorLabel.setFont(Font.font("Arial", 22));
            colorLabel.setTextFill(Color.web("#555"));
            colorBox.getChildren().add(colorLabel);

            String[] colors  = ph.colors;
            Circle[] circles = new Circle[colors.length];
            selectedColor[0] = colors[0];

            for (int i = 0; i < colors.length; i++) {
                Circle c = new Circle(8);
                try { c.setFill(Color.web(colors[i])); }
                catch (Exception ex) { c.setFill(Color.LIGHTGRAY); }
                circles[i] = c;
                if (i == 0) { c.setStroke(Color.web("#555")); c.setStrokeWidth(2); }
                int index = i;
                c.setOnMouseEntered(e -> c.setStyle("-fx-cursor: hand;"));
                c.setOnMouseClicked(e -> {
                    selectedColor[0] = colors[index];
                    for (Circle cr : circles) cr.setStroke(Color.TRANSPARENT);
                    c.setStroke(Color.web("#555")); c.setStrokeWidth(2);
                });
                colorBox.getChildren().add(c);
            }
            colorBox.setAlignment(Pos.CENTER_LEFT);
            content.getChildren().add(colorBox);
            
            //storage
            HBox storageBox = new HBox(10);
            Label storageLabel = new Label("Storage : ");
            storageLabel.setFont(Font.font("Arial", 22));
            storageLabel.setTextFill(Color.web("#555"));
            storageBox.getChildren().add(storageLabel);
            
            String[] phoneStorages = ph.storages;
            selectedStorage[0] = phoneStorages[0];
            
            for (String storage : phoneStorages) {
                Button storageBtn = new Button(storage);
                storageBtn.setStyle(storage.equals(phoneStorages[0])
                    ? "-fx-background-color: linear-gradient(to right, #00c6ff,"
                            + " #0072ff); -fx-text-fill: white;"
                            + " -fx-font-weight: bold; -fx-cursor: hand;"
                    : "-fx-font-weight: bold;");
                storageBtn.setOnMouseEntered(e -> {
                    if (!selectedStorage[0].equals(storage))
                        storageBtn.setStyle("-fx-font-weight: bold; -fx-cursor: hand;");
                });
                storageBtn.setOnMouseExited(e -> {
                    if (!selectedStorage[0].equals(storage))
                        storageBtn.setStyle("-fx-font-weight: bold;");
                });
                storageBtn.setOnMouseClicked(e -> {
                    selectedStorage[0] = storage;
                    for (var node : storageBox.getChildren())
                        if (node instanceof Button btn) btn.setStyle("-fx-font-weight: bold;");
                    storageBtn.setStyle("-fx-background-color: linear-gradient(to right,"
                            + " #00c6ff, #0072ff); -fx-text-fill: white;"
                            + " -fx-font-weight: bold; -fx-cursor: hand;");
                });
                storageBox.getChildren().add(storageBtn);
            }
            content.getChildren().add(storageBox);
        }

        
        // ── Laptops: storage + RAM ──
        if (p instanceof Laptops lp) {
            //storage
            HBox laptopStorageBox = new HBox(10);
            Label laptopStorageLabel = new Label("Storage : ");
            laptopStorageLabel.setFont(Font.font("Arial", 22));
            laptopStorageLabel.setTextFill(Color.web("#555"));
            laptopStorageBox.getChildren().add(laptopStorageLabel);
            
            String[] laptopStorages = lp.storages;
            selectedStorage[0] = laptopStorages[0];
            
            for (String storage : laptopStorages) {
                Button storageBtn = new Button(storage);
                storageBtn.setStyle(storage.equals(laptopStorages[0])
                    ? "-fx-background-color: linear-gradient(to right, #00c6ff,"
                            + " #0072ff); -fx-text-fill: white;"
                            + " -fx-font-weight: bold; -fx-cursor: hand;"
                    : "-fx-font-weight: bold;");
                storageBtn.setOnMouseEntered(e -> {
                    if (!selectedStorage[0].equals(storage))
                        storageBtn.setStyle("-fx-font-weight: bold; -fx-cursor: hand;");
                });
                storageBtn.setOnMouseExited(e -> {
                    if (!selectedStorage[0].equals(storage))
                        storageBtn.setStyle("-fx-font-weight: bold;");
                });
                storageBtn.setOnMouseClicked(e -> {
                    selectedStorage[0] = storage;
                    for (var node : laptopStorageBox.getChildren())
                        if (node instanceof Button btn) btn.setStyle("-fx-font-weight: bold;");
                    storageBtn.setStyle("-fx-background-color: linear-gradient(to right,"
                            + " #00c6ff, #0072ff); -fx-text-fill: white;"
                            + " -fx-font-weight: bold; -fx-cursor: hand;");
                });
                laptopStorageBox.getChildren().add(storageBtn);
            }
            content.getChildren().add(laptopStorageBox);

            // RAM
            HBox ramBox = new HBox(10);
            Label ramLabel = new Label("RAM : ");
            ramLabel.setFont(Font.font("Arial", 22));
            ramLabel.setTextFill(Color.web("#555"));
            ramBox.getChildren().add(ramLabel);
            
            String[] rams = lp.rams;
            selectedRam[0] = rams[0];
            
            for (String ram : rams) {
                Button ramBtn = new Button(ram);
                ramBtn.setStyle(ram.equals(rams[0])
                    ? "-fx-background-color: linear-gradient(to right,"
                            + " #00c6ff, #0072ff); -fx-text-fill: white;"
                            + " -fx-font-weight: bold; -fx-cursor: hand;"
                    : "-fx-font-weight: bold;");
                ramBtn.setOnMouseEntered(e -> {
                    if (!selectedRam[0].equals(ram))
                        ramBtn.setStyle("-fx-font-weight: bold; -fx-cursor: hand;");
                });
                ramBtn.setOnMouseExited(e -> {
                    if (!selectedRam[0].equals(ram))
                        ramBtn.setStyle("-fx-font-weight: bold;");
                });
                ramBtn.setOnMouseClicked(e -> {
                    selectedRam[0] = ram;
                    for (var node : ramBox.getChildren())
                        if (node instanceof Button btn) btn.setStyle("-fx-font-weight: bold;");
                    ramBtn.setStyle("-fx-background-color: linear-gradient(to right,"
                            + " #00c6ff, #0072ff); -fx-text-fill: white;"
                            + " -fx-font-weight: bold; -fx-cursor: hand;");
                });
                ramBox.getChildren().add(ramBtn);
            }
            content.getChildren().add(ramBox);
        }

        // ── DigitalProduct ──
        if (p instanceof DigitalProduct dp) {
            // link
            Label linkLabel = new Label("Download Link: " + dp.downloadLink);
            linkLabel.setTextFill(Color.web("#00c6ff"));
            linkLabel.setStyle("-fx-cursor: hand;");
            linkLabel.setOnMouseClicked(e -> {
                try {
                    if (dp instanceof DigitalDownload dd) {
                        if (dd.downloadLimit > 0) {
                            dd.download();
                            Desktop.getDesktop().browse(new URI(dp.downloadLink));
                        }
                    } else {
                        Desktop.getDesktop().browse(new URI(dp.downloadLink));
                    }
                } catch (Exception ex) { ex.printStackTrace(); }
            });

            // file size
            Label fileSizeLabel = new Label("File Size: " + dp.fileSize + " MB");
            fileSizeLabel.setTextFill(Color.web("#555"));
            fileSizeLabel.setFont(Font.font("Arial", 15));
            fileSizeLabel.setMaxWidth(320);
            fileSizeLabel.setAlignment(Pos.CENTER_LEFT);
            content.getChildren().addAll(linkLabel, fileSizeLabel);

            if (p instanceof DigitalDownload dd) {
                //format
                Label formatLabel = new Label("Format: " + dd.format);
                formatLabel.setTextFill(Color.web("#555"));
                formatLabel.setFont(Font.font("Arial", 15));
                formatLabel.setMaxWidth(320);
                formatLabel.setAlignment(Pos.CENTER_LEFT);

                //remaining
                Label downloadLimitLabel = new Label("Downloads remaining: " + dd.downloadLimit);
                downloadLimitLabel.setTextFill(Color.web("#555"));
                downloadLimitLabel.setFont(Font.font("Arial", 15));
                downloadLimitLabel.setMaxWidth(320);
                downloadLimitLabel.setAlignment(Pos.CENTER_LEFT);
                content.getChildren().addAll(formatLabel, downloadLimitLabel);
            }

            if (p instanceof SoftwareLicense sl) {
                //key
                Label licenseKeyLabel = new Label("License Key: " + sl.licenseKey);
                licenseKeyLabel.setTextFill(Color.web("#555"));
                licenseKeyLabel.setFont(Font.font("Arial", 15));
                licenseKeyLabel.setMaxWidth(320);
                licenseKeyLabel.setAlignment(Pos.CENTER_LEFT);

                //expiry 
                Label expiryLabel = new Label("Expires: " + sl.expiryDate);
                expiryLabel.setTextFill(Color.web("#555"));
                expiryLabel.setFont(Font.font("Arial", 15));
                expiryLabel.setAlignment(Pos.CENTER_LEFT);

                Button activateBtn = new Button("Activate License");
                activateBtn.setStyle("-fx-background-color: linear-gradient(to right,"
                        + " #00c6ff, #0072ff); -fx-text-fill: white;"
                        + " -fx-font-weight: bold; -fx-cursor: hand; -fx-background-radius: 8;");
                boolean[] activated = {false};
                activateBtn.setOnMouseExited(e -> {
                    if (!activated[0])
                        activateBtn.setStyle("-fx-background-color: linear-gradient(to right,"
                                + " #00c6ff, #0072ff); -fx-text-fill: white;"
                                + " -fx-font-weight: bold;"
                                + " -fx-cursor: hand; -fx-background-radius: 8;");
                });
                activateBtn.setOnMouseClicked(e -> {
                    if (sl.activateLicense()) {
                        activated[0] = true;
                        activateBtn.setText("✓ Activated");
                        activateBtn.setStyle("-fx-background-color: #0d9488; -fx-text-fill: white;"
                                + " -fx-font-weight: bold; -fx-background-radius: 8;");
                    } else {
                        activated[0] = true;
                        activateBtn.setText("✗ License Expired");
                        activateBtn.setStyle("-fx-background-color: #e53e3e; -fx-text-fill: white;"
                                + " -fx-font-weight: bold; -fx-background-radius: 8;");
                    }
                });
                HBox expiryBox = new HBox(15, expiryLabel, activateBtn);
                expiryBox.setAlignment(Pos.CENTER_LEFT);
                content.getChildren().addAll(licenseKeyLabel, expiryBox);
            }
        }

        content.getChildren().add(HBoxPrice);

        // ── Buy Button ──
        Button buyBtn = new Button("🛒  Add to Cart");
        buyBtn.setPrefWidth(320);
        buyBtn.setStyle("""
            -fx-background-color: #2563eb;
            -fx-text-fill: white;
            -fx-font-size: 16px;
            -fx-font-weight: bold;
            -fx-padding: 14;
            -fx-background-radius: 8;
        """);
        buyBtn.setOnMouseEntered(e -> buyBtn.setStyle("""
            -fx-background-color: #1d4ed8;
            -fx-text-fill: white;
            -fx-font-size: 16px;
            -fx-font-weight: bold;
            -fx-padding: 14;
            -fx-background-radius: 8;
            -fx-cursor: hand;
        """));
        buyBtn.setOnMouseExited(e -> buyBtn.setStyle("""
            -fx-background-color: #2563eb;
            -fx-text-fill: white;
            -fx-font-size: 16px;
            -fx-font-weight: bold;
            -fx-padding: 14;
            -fx-background-radius: 8;
        """));

        buyBtn.setOnAction(e -> {
            detailStage.close();

            cart.addProduct(p, 1);
            refreshCartUI();
        });

        content.getChildren().add(buyBtn);

       
        Scene detailScene = new Scene(content, 420, 750);
        detailStage.setScene(detailScene);
        detailStage.setResizable(false);
        detailStage.show();
    }

    // =========================================================
    // searchProducts
    // =========================================================
    private void searchProducts(String keyword) {
        productsPane.getChildren().clear();
        Product[] allProducts = {c1, c2, laptop1, laptop2, phone1, phone2, s1, s2, d1, d2};
        for (Product p : allProducts) {
            if (p.name.toLowerCase().contains(keyword.toLowerCase())) {
                productsPane.getChildren().add(createProductCard(p));
            }
        }
    }
}
