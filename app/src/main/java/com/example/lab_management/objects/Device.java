package lehao.com;

public class Device {
    private int maPhong = 1;
    private String tenPhong;
    private int maTb = 1;
    private String tenTb;
    private String loaitb;
    private String tinhtrang;
    private String ngaynhap;
    private String ghichu;

    public Device(int maTb, String tenTb, String loaitb, String tinhtrang, String ngaynhap) {
        this.maTb = maTb;
        this.tenTb = tenTb;
        this.loaitb = loaitb;
        this.tinhtrang = tinhtrang;
        this.ngaynhap = ngaynhap;
    }
    public Device(String tenPhong, int maTb, String tenTb, String loaitb, String tinhtrang, String ngaynhap, String ghichu) {

        this.tenPhong = tenPhong;
        this.maTb = maTb;
        this.tenTb = tenTb;
        this.loaitb = loaitb;
        this.tinhtrang = tinhtrang;
        this.ngaynhap = ngaynhap;
        this.ghichu = ghichu;
    }


    public Device(String tenPhong, String tenTb, String loaitb, String tinhtrang, String ngaynhap, String ghichu) {
        this.tenPhong = tenPhong;
        this.tenTb = tenTb;
        this.loaitb = loaitb;
        this.tinhtrang = tinhtrang;
        this.ngaynhap = ngaynhap;
        this.ghichu = ghichu;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public Device() {
    }


    public int getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(int maPhong) {
        this.maPhong = maPhong;
    }

    public String getTenPhong() {
        return tenPhong;
    }

    public void setTenPhong(String tenPhong) {
        this.tenPhong = tenPhong;
    }

    public int getMaTb() {
        return maTb;
    }

    public void setMaTb(int maTb) {
        this.maTb = maTb;
    }

    public String getTenTb() {
        return tenTb;
    }

    public void setTenTb(String tenTb) {
        this.tenTb = tenTb;
    }

    public String getLoaitb() {
        return loaitb;
    }

    public void setLoaitb(String loaitb) {
        this.loaitb = loaitb;
    }

    public String getTinhtrang() {
        return tinhtrang;
    }

    public void setTinhtrang(String tinhtrang) {
        this.tinhtrang = tinhtrang;
    }
    public String getNgaynhap() {
        return ngaynhap;
    }

    public void setNgaynhap(String ngaynhap) {
        this.ngaynhap = ngaynhap;
    }



    @Override
    public String toString(){
        return "Tên phòng: "+tenPhong+"\n"+
                "Tên thiết bị: "+tenTb+"\n"+
                "Loại thiết bị: "+loaitb+"\n"+
                "Tình trạng: "+ tinhtrang+"\n"+
                "Ngày nhập: "+ngaynhap+"\n"+
                "Ghi chú: "+ghichu;
    }

}
