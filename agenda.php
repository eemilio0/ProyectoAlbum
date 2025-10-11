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
                <th> Puesto</th>
				<th> Empresa</th>
                <th> Descripcion</th>
                <th> Telefono</th>
                <th> Telefono2</th>
                <th> Correo electronico</th>
                <th> Credito</th>
            </tr>
            <div class="container">
<div style="text-align: right;">
<a href="inicio.html" class="btn btn-primary">Regresar</a>
</div>
<?php

$sql="SELECT * from a_provedores";
$result=mysqli_query($conexion,$sql);

while($mostrar=mysqli_fetch_array($result)){
?>

<tr>
<td><?php echo $mostrar['id']?></td>
<td><?php echo $mostrar['Nombre']?></td>
<td><?php echo $mostrar['Puesto']?></td>
<td><?php echo $mostrar['Empresa']?></td>
<td><?php echo $mostrar['Descripcion']?></td>
<td><?php echo $mostrar['Telefono']?></td>
<td><?php echo $mostrar['Telefono2']?></td>
<td><?php echo $mostrar['Correo electronico']?></td>
<td><?php echo $mostrar['Credito']?></td>
</tr>
<?php
}
?>

</table>
</body>
</html>