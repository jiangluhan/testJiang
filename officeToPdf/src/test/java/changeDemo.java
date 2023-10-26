import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

public class changeDemo {
    public static void main(String[] args) {
        try{
            changeDemo.changeMethod();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void changeMethod() throws Exception {
        // 获取jar包 [在搜索路径的开头插入目录或jar（或zip）文件]
        ClassPool.getDefault().insertClassPath("D:\\MySelf\\officeToPdf\\aspose-words-20.7-jdk17.jar");
        // 获取对应需要修改的类文件
        CtClass zzWPbClass = ClassPool.getDefault().getCtClass("com.aspose.words.zzWPb");
        // 基于上述类找到需要修改的方法
        CtMethod[] ctMethods = zzWPbClass.getDeclaredMethods();
        System.out.println(ctMethods.length);
        for (CtMethod ctMethod : ctMethods) {
            CtClass[] ps = ctMethod.getParameterTypes();
            System.out.println(ps.length);
            if (ps.length == 1 && ctMethod.getName().equals("zzY3d")) {
                System.out.println("ps[0].getName==" + ps[0].getName());
                ctMethod.setBody("{}");
            }
        }
        // 将要修改的东西设置在body里面
//        zzY3ds[1].setBody("");
        // 将修改后的类写出文件
        zzWPbClass.writeFile();

        // 获取对应需要修改的类文件
//        CtClass zzaeClass = ClassPool.getDefault().getCtClass("com.aspose.words.zzae");
//        // 基于上述类找到需要修改的方法
//        CtMethod zzZF8 = zzaeClass.getDeclaredMethod("zzZF8");
//        // 将要修改的东西设置在body里面
//        zzZF8.setBody("{return 256;}");
//        // 将修改后的类写出文件
//        zzaeClass.writeFile();
    }
}
