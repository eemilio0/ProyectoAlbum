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
				<th> Proveedor</th>
                <th> Plan de produccion</th>
                <th> No.Piezas</th>
                <th> Precio total</th>
                <th> Orden de trabajo</th>
                <th> P/Material</th>
                <th> P/Herramienta</th>
                <th> Total de inversion</th>
                <th> Certificado de calidad</th>
                <th> Comentarios</th>
            </tr>
            <div class="container">
<div style="text-align: right;">
<a href="inicio.html" class="btn btn-primary">Regresar</a>
</div>
<?php

$sql="SELECT * from produccion";
$result=mysqli_query($conexion,$sql);

while($mostrar=mysqli_fetch_array($result)){
?>

<tr>
<td><?php echo $mostrar['id']?></td>
<td><?php echo $mostrar['nombre']?></td>
<td><?php echo $mostrar['subelementos']?></td>
<td><?php echo $mostrar['proveedor']?></td>
<td><?php echo $mostrar['plan_produccion']?></td>
<td><?php echo $mostrar['no.piezas']?></td>
<td><?php echo $mostrar['precio_total']?></td>
<td><?php echo $mostrar['orden_trabajo']?></td>
<td><?php echo $mostrar['p/material']?></td>
<td><?php echo $mostrar['p/herramienta']?></td>
<td><?php echo $mostrar['total_inversion']?></td>
<td><?php echo $mostrar['certificado_calidad']?></td>
<td><?php echo $mostrar['comentarios']?></td>
</tr>
<?php
}
?>

</table>
</body>
</html>