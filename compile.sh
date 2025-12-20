rm sources.txt
find src -name "*.java" > sources.txt
javac -cp biuoop-1.4.jar:src @sources.txt -d build

LINE_COUNT=$(wc -l sources.txt)
echo "Compiled $LINE_COUNT files"

while getopts r: flag
do
    case "${flag}" in
        r) file=${OPTARG};;
    esac
done

if [ -z "$file" ]
    then
        echo "Not running, finished"
    else
        echo "Running build..."
        java -cp biuoop-1.4.jar:build "$file"
fi