<?php
$conexion=mysqli_connect('localhost','root','','almacenycompras');

?>
<html>
<head>
<title>Mostrar datos</title>
<link rel="stylesheet" href=".css">
</head>
<body>
<table border="1">
            <tr>
                <th> Id</th>
                <th> Nombre</th>
                <th> Subelementos</th>
				<th> Piezas</th>
                <th> Estado</th>
                <th> Ultima Actualizacion</th>
            </tr>
            <div class="container">
<div style="text-align: right;">
<a href="inicio.html" class="btn btn-primary">Regresar</a>
</div>
<?php

$sql="SELECT * from inventario";
$result=mysqli_query($conexion,$sql);

while($mostrar=mysqli_fetch_array($result)){
?>

<tr>
<td><?php echo $mostrar['id']?></td>
<td><?php echo $mostrar['Nombre']?></td>
<td><?php echo $mostrar['Subelementos']?></td>
<td><?php echo $mostrar['Piezas']?></td>
<td><?php echo $mostrar['Estado']?></td>
<td><?php echo $mostrar['Ultima actualizacion']?></td>
<?php
}
?>

</table>
</body>
</html>