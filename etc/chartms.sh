#!/bin/bash

### BEGIN INIT INFO
# Provides: chartms
# Required-Start: $network
# Required-Stop: $network
# Should-Start:
# Should-Stop:
# Default-Start: 2 3 4 5
# Default-Stop: 0 1 6
# Short-Description: Start up the Chart MS service
# Description:
### END INIT INFO

HOMEDIR=/opt/chartms
LOGDIR=/var/log/chartms
LOGFILE=$LOGDIR/chartms.log
JAVA_HOME=/usr/lib/jvm/java-8-oracle/jre/bin/java
APPNAME=chart-ms-service
JARFILE=$APPNAME.jar
USER=chartms
JAVA_OPTS="-Xmx96m -Dcom.sun.management.jmxremote.port=3333 \
-Dcom.sun.management.jmxremote.ssl=false \
-Dcom.sun.management.jmxremote.authenticate=false"
SERVER_PORT=8082
WORKINGDIR=$HOMEDIR/working
PIDFILE=$HOMEDIR/chartms.pid
SPRING_OPTS="--server.port=$SERVER_PORT --app.workingDir=$WORKINGDIR --logging.file=$LOGFILE"

URL=http://localhost:$SERVER_PORT/api/ping
STARTUP_CHECK_LIMIT=5
SLEEPTIME=5

init() {
  # see if the logs dir exists
  if [ ! -d "$LOGDIR" ]; then
    echo "Initializing log directory"
    mkdir $LOGDIR
    chown $USER:$USER $LOGDIR
    chmod 775 $LOGDIR
  fi
}

get_pid() {
  echo `ps -aux | grep $APPNAME | grep -v grep | awk '{ print $2 }'`
}

get_ping_status() {
  wget -qO- --server-response $URL 2>&1 | grep "HTTP/" | awk '{print $2}'
}

do_start() {
  init
  pid=$(get_pid)
  if [ -n "$pid" ]
  then
    echo "Chart MS is already running (pid: $pid)"
  else
    echo "Starting Chart MS..."
    /bin/su -m -c "$JAVA_HOME -jar $JAVA_OPTS $HOMEDIR/$JARFILE $SPRING_OPTS &>/dev/null &" $USER
    #Check the api/ping status
    count=1
    is_up="no"
    while [ "$is_up" = "no" ] && [ $count -lt $STARTUP_CHECK_LIMIT ];
    do
      echo "Checking deployment status..."
      status=$(get_ping_status)
      if [ "$status" = "200" ]
      then
        is_up="yes"
        echo "Chart MS started."
      else
        sleep $SLEEPTIME
        let count=$count+1
      fi
    done
    if [ "$is_up" = "no" ]
    then
      echo "ERROR starting application.  Check logs in $LOGFILE"
    fi
  fi
  return 0
}

do_stop() {
  pid=$(get_pid)
    if [ -n "$pid" ]
    then
        echo "Stopping ChartMS process $pid"
        kill -9 $pid
        count=1
        is_up="yes"
        while [ "$is_up" = "yes" ] && [ $count -lt $STARTUP_CHECK_LIMIT ];
        do
          status=$(get_ping_status)
          if [ "$status" = "" ]
          then
            is_up="no"
            echo "Chart MS stopped."
          else
            sleep $SLEEPTIME
            let count=$count+1
          fi
        done
        if [ "$is_up" != "no" ]
        then
          echo "ERROR stopping application.  Check logs in $LOGDIR"
        fi
        else
            echo "ChartMS is not running"
        fi
    return 0
}

case "$1" in
    start)
        do_start
        ;;
    stop)
        do_stop
        ;;
    restart)
        do_stop
        do_start
        ;;
    status)
      pid=$(get_pid)
        if [ -n "$pid" ]
        then
           echo "Chart MS is running with pid: $pid"
        else
           echo "Chart MS is not running"
        fi
        ;;
     *)
        echo "Usage: $0 {start|stop|restart|status}"
     exit 1
esac
exit 0