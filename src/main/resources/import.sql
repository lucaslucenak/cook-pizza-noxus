insert into status(status, created_at, updated_at) values ('ACTIVE', NOW(), NOW());

insert into size(size, created_at, updated_at) values ('LARGE', NOW(), NOW());

insert into flavor(flavor, price, created_at, updated_at) values ('Frango com Catupiry', 25.0, NOW(), NOW());
insert into flavor(flavor, price, created_at, updated_at) values ('Calabresa', 25.0, NOW(), NOW());

insert into edge(edge, price, created_at, updated_at) values ('Tradicional', 0.0, NOW(), NOW());
insert into edge(edge, price, created_at, updated_at) values ('Catupiry', 10.0, NOW(), NOW());

insert into drink(name, price, created_at, updated_at) values('Coca-Cola 1L', 8.0, NOW(), NOW());

insert into delivery_tax(tax, neighbourhood, created_at, updated_at) values(7.0, 'São José', NOW(), NOW());

insert into payment_method(payment_method, created_at, updated_at) values ('CREDIT_CARD', NOW(), NOW());

insert into delivery_type(delivery_type, created_at, updated_at) values ('DELIVERY', NOW(), NOW());

insert into customer_account(first_name, last_name, cpf, email, cellphone_number, status_id, created_at, updated_at) values ('Lucas', 'Siqueira', '108.917.264-89', 'lucas.lucenak@gmail.com', '83986907270', 1, NOW(), NOW());
