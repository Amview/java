1. git init

2. git add .

3. git commit -m '注释内容'

4. ssh-keygen -t rsa -C  "邮箱"

5. git remote add origin 仓库

6. git config --global user.email ""

   git config --global user.name ""

7. git push -u origin main



git branch -M main

echo "# linux" >> README.md
git init
git add README.md
git commit -m "first commit"
git branch -M main
git remote add origin git@github.com:Amview/linux.git
git push -u origin main


