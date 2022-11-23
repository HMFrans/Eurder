insert into address
values(nextval('address_seq'), 'Leuven', '3000', 'Bondgenotenlaan', '15', '');

insert into address
values(nextval('address_seq'), 'Leuven', '3000', 'Ladeuzeplein', '15', '');

insert into customer
values (nextval('customer_seq'), 'password', 'admin', 'master', 'admin@test.com', '555/555555', 1, 'ADMIN' );

insert into customer
values (nextval('customer_seq'), 'password', 'customer', 'paying', 'customer@test.com', '555/555555', 2, 'MEMBER' );

