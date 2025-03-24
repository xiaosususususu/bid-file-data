package cn.zhongzhi.service;

import cn.zhongzhi.domain.FileSystem;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author suchengbo
 */
public interface FileSystemService extends IService<FileSystem> {
    /**
     * 保存文件或文件夹
     * @param fullPath 完整路径
     */
    String saveFileOrFolder(String fullPath);
}