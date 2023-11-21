INSERT INTO pizzas (name, description, image, price) VALUES ("Margherita", "Pomodoro, mozzarella", "https://www.anticaosteriadawalter.it/immagini/pizza-storia.jpg", 5.00);
INSERT INTO pizzas (name, description, image, price) VALUES ("Patate", "Pomodoro, mozzarella, patatine fritte", "https://blog.giallozafferano.it/iannamaria/wp-content/uploads/2016/08/pizza-con-patatine-e-salsiccia.jpg", 6.00);
INSERT INTO pizzas (name, description, image, price) VALUES ("Funghi", "Pomodoro,mozzarella, funghi", "https://www.petitchef.it/imgupl/recipe/pizza-ai-funghi-champignons--452646p700939.jpg", 6.50);
INSERT INTO pizzas (name, description, image, price) VALUES ("Crudo", "Pomodoro, mozzarella, crudo", "https://www.petitchef.it/imgupl/recipe/pizza-al-prosciutto-crudo--450186p695926.jpg", 6.50);
INSERT INTO pizzas (name, description, image, price) VALUES ("Rucola", "Pomodoro, mozzarella, rucola", "https://blog.giallozafferano.it/coloriesaporiincucina/wp-content/uploads/2021/01/pizza-con-speck-e-rucola.jpg", 7.00);
INSERT INTO pizzas (name, description, image, price) VALUES ("Diavola", "Pomodoro, mozzarella, salsiccia piccante", "https://pizzeriavesuviana.pl/wp-content/uploads/2021/09/Nowosc-Pizza-Salsiccia-Piccante.jpg", 7.00);

INSERT INTO ingredients(name) VALUES("pomodoro");
INSERT INTO ingredients(name) VALUES("mozzarella");
INSERT INTO ingredients(name) VALUES("basilico");
INSERT INTO ingredients(name) VALUES("gorgonzola");
INSERT INTO ingredients(name) VALUES("salsiccia");
INSERT INTO ingredients(name) VALUES("patate");
INSERT INTO ingredients(name) VALUES("rucola");
INSERT INTO ingredients(name) VALUES("crudo");
INSERT INTO ingredients(name) VALUES("funghi");


INSERT INTO roles (id, name) VALUES(1, 'ADMIN');
INSERT INTO roles (id, name) VALUES(2, 'USER');

INSERT INTO users (email, first_name, last_name, registered_at, password) VALUES('nicola@email.com', 'Nicola', 'Soggiu', '2023-11-20 10:35', '{noop}nicola');
INSERT INTO users (email, first_name, last_name, registered_at, password) VALUES('Mario@email.com', 'Mario', 'Rossi', '2023-11-20 10:35','{noop}mario');

INSERT INTO users_roles (user_id, roles_id) VALUES(1, 1);
INSERT INTO users_roles (user_id, roles_id) VALUES(1, 2);
INSERT INTO users_roles (user_id, roles_id) VALUES(2, 2);