#!/bin/sh


# Esta SHELL ejecuta el JOB que transfiere los datos a Osinergmin
# 1 (NOMBRES DE JOBs A EJECUTARSE) ejem: JOB01
echo "::::::::: Inicio JOBOSI Osinergmin para la tabla 1 :::::::::::"
horaminu=`date +%Y%m%d`
archivo="/logs/sc4j/jobosi_$horaminu.log"

x=1
while true
do
hora=`date +%H%M`
minu=`date +%M`
dia=`date|cut -c 1-3`
hfin=2200
if [ $hora -ge $hfin ]
then
   exit 0
fi

echo "*INICIA PROCESO $x" >> ${archivo}
date >> ${archivo}


#Ejecuta Transferencia Archivos
echo "* Inicio JOBOSI Osinergmin" >> ${archivo}
date  >> ${archivo}
cantidad=`ps -fea|grep JOBOSI-1.0-RELEASE.jar | grep -v "grep" | wc -l`
if [ $cantidad -lt 1 ]; then
     java -jar JOBOSI-1.0-RELEASE.jar JOB01
fi
date  >> ${archivo}
echo "* Fin JOBOSI Osinergmin" >> ${archivo}

x=` expr $x + 1 `

sleep 900

done


