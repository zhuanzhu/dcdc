echo "start";
DIR="$( cd "$( dirname "$0"  )" && pwd  )"
cd "$DIR"
# find pid and kill
pidstr=`ps -ef  | grep java | grep "$DIR"  | grep -v 'grep ' | awk '{print $2}'`;
echo $pidstr;
kill -9  $pidstr;
echo "end";
