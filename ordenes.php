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
				<th> Fecha</th>
                <th> Empresa</th>
                <th> Direccion</th>
                <th> Vendedor</th>
                <th> Archivo</th>
                <th> Certificado de calidad</th>
            </tr>
            <div class="container">
<div style="text-align: right;">
<a href="inicio.html" class="btn btn-primary">Regresar</a>
</div>
<?php

$sql="SELECT * from o_compra";
$result=mysqli_query($conexion,$sql);

while($mostrar=mysqli_fetch_array($result)){
?>

<tr>
<td><?php echo $mostrar['id']?></td>
<td><?php echo $mostrar['Nombre']?></td>
<td><?php echo $mostrar['Subelementos']?></td>
<td><?php echo $mostrar['Fecha']?></td>
<td><?php echo $mostrar['Empresa']?></td>
<td><?php echo $mostrar['Direccion']?></td>
<td><?php echo $mostrar['Vendedor']?></td>
<td><?php echo $mostrar['Archivo']?></td>
<td><?php echo $mostrar['Certificado de calidad']?></td>
</tr>
<?php
}
?>

</table>
</body>
</html>