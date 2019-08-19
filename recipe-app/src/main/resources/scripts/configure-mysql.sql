create schema sfg_dev;
create schema sfg_prod;

CREATE USER 'sfg_dev_user'@'%' IDENTIFIED BY 'password';
GRANT SELECT, INSERT, UPDATE, DELETE ON sfg_dev.* TO 'sfg_dev_user'@'%';

CREATE USER 'sfg_prod_user'@'%' IDENTIFIED BY 'password';
GRANT SELECT, INSERT, UPDATE, DELETE ON sfg_prod.* TO 'sfg_prod_user'@'%';