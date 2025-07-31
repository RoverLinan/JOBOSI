JOBOSI.sh
#!/bin/sh

# Esta SHELL ejecuta el JOB que transfiere los datos a Osinergmin
#Recibe 3 parametros :
# 1 (NOMBRES DE JOBs A EJECUTARSE) ejem: JOB01 o JOB02-JOB03-JOB04
# 2 (Periodo de remision) ejem: 13/07/2025,
# 3 (ubicacion de configuracion BD <Opcional>) ejem: /config/datasource_jobosi.properties
date

echo "Inicio JOBOSI Osinergmin"
horaminu=`date +%Y%m%d`
archivo="/logs/sc4j/jobosi_$horaminu.log"

echo "*INICIA PROCESO $x" >> ${archivo}
date >> ${archivo}


#Ejecuta Transferencia Archivos
echo "* Inicio JOBOSI Osinergmin" >> ${archivo}
date  >> ${archivo}
cantidad=`ps -fea|grep JOBOSI-1.0-RELEASE.jar | grep -v "grep" |wc -l`
if [ $cantidad -le 1 ]; then
    java -jar C:/Users/Rover/JOBOSI-1.0-RELEASE.jar $1 $2 $3
fi
date  >> ${archivo}
echo "* Fin JOBOSI Osinergmin" >> ${archivo}


chmod 777  ${archivo}
date >> ${archivo}
echo "* FIN PROCESO " >> ${archivo}