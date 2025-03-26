package cn.zhongzhi.service.impl;

import cn.hutool.core.text.StrPool;
import cn.hutool.core.util.ObjUtil;
import cn.zhongzhi.domain.FileSystem;
import cn.zhongzhi.enums.FileTypeEnum;
import cn.zhongzhi.mapper.FileSystemMapper;
import cn.zhongzhi.service.FileSystemService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author suchengbo
 */
@Service
public class FileSystemServiceImpl extends ServiceImpl<FileSystemMapper, FileSystem> implements FileSystemService {


    @Resource
    private FileSystemMapper fileSystemMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public String saveFileOrFolder(String fullPath) {
        String[] pathStrArr = fullPath.split(StrPool.SLASH);
        String parentId = "";
        for (int i = 0; i < pathStrArr.length - 1; i++) {
            //每一级都需要先判断是否存在 ， i为层级 ，path为名称
            String dirName = pathStrArr[i];
            QueryWrapper<FileSystem> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("name", dirName)
                    .eq("depth", i);
            //先查询，查询到有了，直接返回
            FileSystem fileSystem = fileSystemMapper.selectOne(queryWrapper);
            if (ObjUtil.isNotEmpty(fileSystem)) {
                parentId = fileSystem.getId();
                continue;
            }
            FileSystem fileSystemEntity = FileSystem.builder().name(dirName).type(FileTypeEnum.FOLDER.name()).depth(i).parentId(parentId).build();
            fileSystemMapper.insert(fileSystemEntity);
            //重新赋值parentId
            parentId = fileSystemEntity.getId();
        }
        //存文件
        String fileName = pathStrArr[pathStrArr.length - 1];
        //取到fileName的后缀
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
        FileSystem fileSystemFile = FileSystem.builder().name(pathStrArr[pathStrArr.length - 1]).type(FileTypeEnum.FILE.name()).extension(extension).path(fullPath).depth(pathStrArr.length - 1).parentId(parentId).build();
        //判断是否存在
        FileSystem fileSystemOne = fileSystemMapper.selectOne(new QueryWrapper<FileSystem>().eq("path", fullPath));
        if (ObjUtil.isEmpty(fileSystemOne)) {
            fileSystemMapper.insert(fileSystemFile);
            return fileSystemFile.getId();
        } else {
            return fileSystemOne.getId();
        }
    }

    @Override
    public String fileIdExchangeFullPath(String fileId) {
        //根据文件ID查询path
        FileSystem fileSystem = fileSystemMapper.selectById(fileId);
        if (ObjUtil.isEmpty(fileSystem)) {
            return null;
        }
        return fileSystem.getPath();
    }


}