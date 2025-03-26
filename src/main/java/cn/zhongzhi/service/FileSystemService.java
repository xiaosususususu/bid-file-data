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

    /**
     * 文件ID交换路径
     * @param fileId 文件ID
     * @return 全路径
     */
    String fileIdExchangeFullPath(String fileId);
}