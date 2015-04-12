# Privilegios para `UsuarioP1`@`localhost`

GRANT USAGE ON *.* TO 'UsuarioP1'@'localhost';

GRANT SELECT ON `practica1_606`.* TO 'UsuarioP1'@'localhost';

GRANT INSERT, UPDATE ON `practica1_606`.`historial` TO 'UsuarioP1'@'localhost';

GRANT INSERT, UPDATE, DELETE ON `practica1_606`.`peticiones` TO 'UsuarioP1'@'localhost';

GRANT INSERT, DELETE ON `practica1_606`.`amigos` TO 'UsuarioP1'@'localhost';

GRANT INSERT, UPDATE ON `practica1_606`.`usuarios` TO 'UsuarioP1'@'localhost';

GRANT INSERT ON `practica1_606`.`activos` TO 'UsuarioP1'@'localhost';