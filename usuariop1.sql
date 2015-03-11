GRANT USAGE ON *.* TO 'UsuarioP1'@'localhost';

GRANT SELECT ON `Practica1_606`.* TO 'UsuarioP1'@'localhost';

GRANT UPDATE ON `Practica1_606`.`Usuarios` TO 'UsuarioP1'@'localhost';

GRANT UPDATE (usuario_target, usuario_source) ON `Practica1_606`.`Crucigramas` TO 'UsuarioP1'@'localhost';

GRANT INSERT, UPDATE ON `Practica1_606`.`Historial` TO 'UsuarioP1'@'localhost';

GRANT INSERT, DELETE ON `Practica1_606`.`Amigos` TO 'UsuarioP1'@'localhost';

GRANT INSERT ON `Practica1_606`.`Activos` TO 'UsuarioP1'@'localhost';
