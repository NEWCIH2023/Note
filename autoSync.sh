git add .;
git commit -m 'auto sync from autoSync.sh';
git pull --no-edit;
git push;
curl http://msg:8888/batch.php/index/autoDaySessReport;

