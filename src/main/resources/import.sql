insert into status(status) values ('ACTIVE');

insert into size(size) values ('LARGE');

insert into flavor(flavor, price) values ('Frango com Catupiry', 25.0);
insert into flavor(flavor, price) values ('Calabresa', 25.0);

insert into edge(edge, price) values ('Tradicional', 0.0);
insert into edge(edge, price) values ('Catupiry', 10.0);

insert into drink(name, price) values('Coca-Cola 1L', 8.0)

insert into delivery_tax(tax, neighbourhood) values(7.0, 'São José');

insert into payment_method(payment_method) values ('CREDIT_CARD');

insert into delivery_type(delivery_type) values ('DELIVERY');

insert into customer_account(first_name, last_name, cpf, email, cellphone_number, status_id) values ('Lucas', 'Siqueira', '108.917.264-89', 'lucas.lucenak@gmail.com', '83986907270', 1);