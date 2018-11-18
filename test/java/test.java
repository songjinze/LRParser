public class test {
    public static void main(String args[]){
        String str="###12#3";
        String[]res=str.split("#");
        System.out.println(res.length);
        for(String s:res){
            System.out.println(s);
        }
    }
}
