title 打包全部文件（测试打包时将此文件放在项目src同级路径下）
@echo off


@rem 得到参数
set CURRENT_PATH=%cd%
set JAVA_PATH=%CURRENT_PATH%
echo ++++++++++++++++current path %CURRENT_PATH% ++++++++++++++++++


@rem 进入netty-http打包整个项目
echo ++++++++++++++++mvn install start++++++++++++++++
cd %CURRENT_PATH%
call mvn clean install
echo ++++++++++++++++mvn install end++++++++++++++++


echo ++++++++++++++++delete target start++++++++++++++++
rd /s /q "%JAVA_PATH%\target"
echo ++++++++++++++++delete target end++++++++++++++++


echo ok!
pause