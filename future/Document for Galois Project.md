# Document for Galois Project

[TOC]

## Architecture

```flow
st=>start: SpringBoot项目启动
fileWatch=>operation: Galois开始文件监听
sourceChange=>condition: 是否是源码目录的文件变更？
reload=>operation: Galois重编译源码，并复制到cp目录
isJavaChange=>condition: 是否是Java文件变更
isMyBatisXmlChange=>condition: 是否是MyBatis的XML文件变更
isExtendedFileChange=>condition: 是否是待扩展文件变更
reInstallSpring=>operation: 重新编译，使用ReloadClassLoader装载新类，令Spring重新装入该Bean
reInstallMyBatis=>operation: 令MyBatis重新加载该XML实体
reInstallExtended=>operation: 扩展文件处理
loopEnd=>end: 本次文件监听处理结束

st->fileWatch->sourceChange
sourceChange(no)->fileWatch
sourceChange(yes)->reload

reload->isJavaChange

isJavaChange(yes)->reInstallSpring
isJavaChange(no)->isMyBatisXmlChange
isMyBatisXmlChange(yes)->reInstallMyBatis
isMyBatisXmlChange(no)->isExtendedFileChange
isExtendedFileChange(yes)->reInstallExtended
isExtendedFileChange(no)->loopEnd


reInstallSpring->loopEnd
reInstallMyBatis->loopEnd
reInstallExtended->loopEnd

```





## Coding



