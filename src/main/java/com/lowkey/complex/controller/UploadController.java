package com.lowkey.complex.controller;

import com.google.common.collect.Lists;
import com.lowkey.complex.entity.User;
import com.lowkey.complex.response.ResultEntity;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Controller
public class UploadController {
    @RequestMapping("/upload")
    @ResponseBody
    public ResultEntity<String> uploadFile(HttpServletRequest request, HttpServletResponse response) {
        //得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
        String savePath = request.getServletContext().getContextPath();
        File file = new File(savePath);
        //判断上传文件的保存目录是否存在
        if (!file.exists() && !file.isDirectory()) {
            System.out.println(savePath + "目录不存在，需要创建");
            //创建目录
            boolean mkdir = file.mkdir();
            if (!mkdir) {
                System.out.println("目录创建失败");
            }

        }
        //消息提示
        String message = "";
        try {
            //多部份解析器
            CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
            if (commonsMultipartResolver.isMultipart(request)) {
                MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
                // 获取文件名迭代器
                Iterator<String> iteratorFileNames = multipartHttpServletRequest.getFileNames();
                while (iteratorFileNames.hasNext()) {
                    MultipartFile multipartFile = multipartHttpServletRequest.getFile(iteratorFileNames.next());
                    if (Objects.isNull(multipartFile)) {

                        System.out.println(iteratorFileNames.hasNext());
                    } else {//如果fileitem中封装的是上传文件
                        //得到上传的文件名称，
                        String filename = multipartFile.getName();
                        System.out.println(filename);
                        if (filename.trim().equals("")) {
                            continue;
                        }
                        //注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：  c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
                        //处理获取到的上传文件的文件名的路径部分，只保留文件名部分
                        filename = filename.substring(filename.lastIndexOf("\\") + 1);
                        //获取item中的上传文件的输入流
                        InputStream in = multipartFile.getInputStream();
                        //创建一个文件输出流
                        FileOutputStream out = new FileOutputStream(savePath + "\\" + filename);
                        //创建一个缓冲区
                        byte[] buffer = new byte[1024];
                        //判断输入流中的数据是否已经读完的标识
                        int len = 0;
                        //循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
                        while ((len = in.read(buffer)) > 0) {
                            //使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
                            out.write(buffer, 0, len);
                        }
                        //关闭输入流
                        in.close();
                        //关闭输出流
                        out.close();
                        message = "文件上传成功！";
                    }
                }
            }
        } catch (Exception e) {
            message = "文件上传失败！";
            e.printStackTrace();

        }
        return ResultEntity.successWithData(message);
    }

    public ResultEntity<Map<String, String>> fileList(HttpServletRequest request, HttpServletResponse response) {
        //获取上传文件的目录
        String uploadFilePath = request.getServletContext().getRealPath("/WEB-INF/upload");
        //存储要下载的文件名
        Map<String, String> fileNameMap = new HashMap<String, String>();
        //递归遍历filepath目录下的所有文件和目录，将文件的文件名存储到map集合中
        listfile(new File(uploadFilePath), fileNameMap);//File既可以代表一个文件也可以代表一个目录
        return ResultEntity.successWithData(fileNameMap);
    }

    /**
     * @param file 即代表一个文件，也代表一个文件目录
     * @param map  存储文件名的Map集合
     * @Method: listfile
     * @Description: 递归遍历指定目录下的所有文件
     */
    public void listfile(File file, Map<String, String> map) {
        //如果file代表的不是一个文件，而是一个目录
        if (!file.isFile()) {
            //列出该目录下的所有文件和目录
            File[] files = file.listFiles();
            //遍历files[]数组
            for (File f : files) {
                //递归
                listfile(f, map);
            }
        } else {
            /*
             * 处理文件名，上传后的文件是以uuid_文件名的形式去重新命名的，去除文件名的uuid_部分
             file.getName().indexOf("_")检索字符串中第一次出现"_"字符的位置，如果文件名类似于：9349249849-88343-8344_阿_凡_达.avi
             那么file.getName().substring(file.getName().indexOf("_")+1)处理之后就可以得到阿_凡_达.avi部分
             */
            String realName = file.getName().substring(file.getName().indexOf("_") + 1);
            //file.getName()得到的是文件的原始名称，这个名称是唯一的，因此可以作为key，realName是处理过后的名称，有可能会重复
            map.put(file.getName(), realName);
        }
    }

    /**
     * 下载文件
     *
     * @param request  request请求对象
     * @param response response相应对象
     * @param user     请求参数-接收前端formData对象
     */
    @RequestMapping("/download")
    @ResponseBody
    public void downloadFile(HttpServletRequest request, HttpServletResponse response, User user) throws IOException {
        //得到要下载的文件名
        String fileName = request.getParameter("filename");
        fileName = new String(fileName.getBytes("iso8859-1"), StandardCharsets.UTF_8);
        //上传的文件都是保存在/WEB-INF/upload目录下的子目录当中
        String fileSaveRootPath = request.getServletContext().getRealPath("/WEB-INF/upload");
        //通过文件名找出文件的所在目录
        String path = findFileSavePathByFileName(fileName, fileSaveRootPath);
        //得到要下载的文件
        File file = new File(path + "\\" + fileName);
        //如果文件不存在
        if (!file.exists()) {
            request.setAttribute("message", "您要下载的资源已被删除！！");
            return;
        }
        //处理文件名
        String realName = fileName.substring(fileName.indexOf("_") + 1);
        //设置响应头，控制浏览器下载该文件，此处的文件名要进行编码处理否则前端显示会乱码
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(realName, String.valueOf(StandardCharsets.UTF_8)));
        try (//读取要下载的文件，保存到文件输入流
             FileInputStream in = new FileInputStream(path + "\\" + fileName);
             //创建输出流
             OutputStream out = response.getOutputStream()) {

            //创建缓冲区
            byte[] buffer = new byte[1024];
            int len = 0;
            //循环将输入流中的内容读取到缓冲区当中
            while ((len = in.read(buffer)) > 0) {
                //输出缓冲区的内容到浏览器，实现文件下载
                out.write(buffer, 0, len);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param filename     要下载的文件名
     * @param saveRootPath 上传文件保存的根目录，也就是/WEB-INF/upload目录
     * @return 要下载的文件的存储目录
     * @Method: findFileSavePathByFileName
     * @Description: 通过文件名和存储上传文件根目录找出要下载的文件的所在路径
     * @Anthor:孤傲苍狼
     */
    public String findFileSavePathByFileName(String filename, String saveRootPath) {
        int hashcode = filename.hashCode();
        int dir1 = hashcode & 0xf;  //0--15
        int dir2 = (hashcode & 0xf0) >> 4;  //0-15
        String dir = saveRootPath + "\\" + dir1 + "\\" + dir2;  //upload\2\3  upload\3\5
        File file = new File(dir);
        if (!file.exists()) {
            //创建目录
            file.mkdirs();
        }
        return dir;
    }

    public void traverseFolder1(String path) {
        int fileNum = 0, folderNum = 0;
        File file = new File(path);
        if (file.exists()) {
            LinkedList<File> list = new LinkedList<File>();
            File[] files = file.listFiles();
            for (File file2 : files) {
                if (file2.isDirectory()) {
                    System.out.println("文件夹:" + file2.getAbsolutePath());
                    list.add(file2);
                    folderNum++;
                } else {
                    System.out.println("文件:" + file2.getAbsolutePath());
                    fileNum++;
                }
            }
            File temp_file;
            while (!list.isEmpty()) {
                temp_file = list.removeFirst();
                files = temp_file.listFiles();
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        System.out.println("文件夹:" + file2.getAbsolutePath());
                        list.add(file2);
                        folderNum++;
                    } else {
                        System.out.println("文件:" + file2.getAbsolutePath());
                        fileNum++;
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
        System.out.println("文件夹共有:" + folderNum + ",文件共有:" + fileNum);

    }

    public void traverseFolder2(String path) {

        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (null == files || files.length == 0) {
                System.out.println("文件夹是空的!");
                return;
            } else {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        System.out.println("文件夹:" + file2.getAbsolutePath());
                        traverseFolder2(file2.getAbsolutePath());
                    } else {
                        System.out.println("文件:" + file2.getAbsolutePath());
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
    }

    public static List<File> getFileList(String strPath) {
        List<File> fileList = Lists.newArrayList();
        File dir = new File(strPath);
        File[] files = dir.listFiles(); // 该文件目录下文件全部放入数组
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                String fileName = files[i].getName();
                if (files[i].isDirectory()) { // 判断是文件还是文件夹
                    getFileList(files[i].getAbsolutePath()); // 获取文件绝对路径
                } else if (fileName.endsWith("avi")) { // 判断文件名是否以.avi结尾
                    String strFileName = files[i].getAbsolutePath();
                    System.out.println("---" + strFileName);
                    fileList.add(files[i]);
                } else {
                    continue;
                }
            }

        }
        return fileList;
    }

    @RequestMapping(value = "/downloadFile")
    @ResponseBody
    public void downloadFile(HttpServletResponse response) {
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            File file = new File("D:/javaweb/demo.txt");
            // Spring工具获取项目resources里的文件
            File file2 = ResourceUtils.getFile("classpath:shell/init.sh");
            if (!file.exists()) {
                return;
            }
            response.reset();
            response.setHeader("Content-Disposition", "attachment;filename=demo.txt");
            response.setContentType("application/octet-stream; charset=utf-8");
            os.write(FileUtils.readFileToByteArray(file));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(os);
        }

    }

    /**
     * Spring下载文件
     * 利用 ResponseEntity<byte[]> 实现下载单个文件的方法：
     */
    @RequestMapping(value = "/download/file")
    public ResponseEntity<byte[]> download(HttpServletRequest request) throws IOException {
        // 获取项目webapp目录路径下的文件
        String path = request.getSession().getServletContext().getRealPath("/");
        File file = new File(path + "/soft/javaweb.txt");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "javaweb.txt");
        return new ResponseEntity<byte[]>(org.apache.commons.io.FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
    }
}