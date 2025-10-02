JOBOSI.sh
#!/bin/sh

# Esta SHELL ejecuta el JOB que transfiere los datos a Osinergmin
# 1 (NOMBRES DE JOBs A EJECUTARSE) ejem: JOB02-JOB03-JOB04
date

echo ":::::::::: Inicio JOBOSI Osinergmin para las tablas 2 al 8 :::::::::::"
horaminu=`date +%Y%m%d`
archivo="/logs/sc4j/jobosi_$horaminu.log"

echo "*INICIA PROCESO $x" >> ${archivo}
date >> ${archivo}


#Ejecuta Transferencia Archivos
echo "* Inicio JOBOSI Osinergmin" >> ${archivo}
date  >> ${archivo}
cantidad=`ps -fea|grep JOBOSI-1.0-RELEASE.jar | grep -v "grep" |wc -l`
if [ $cantidad -le 1 ]; then
    java -jar JOBOSI-1.0-RELEASE.jar JOB02-JOB03-JOB04-JOB05-JOB06-JOB07-JOB08
fi
date  >> ${archivo}
echo "* Fin JOBOSI Osinergmin" >> ${archivo}


chmod 777  ${archivo}
date >> ${archivo}
echo "* FIN PROCESO " >> ${archivo}