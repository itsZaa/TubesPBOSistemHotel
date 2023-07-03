package controller;

import java.util.ArrayList;
import java.util.HashMap;

import model.FnBOrder;
import model.LaundryOrder;
import model.Order;
import model.PaymentMethod;
import model.RoomOrder;
import view.PaymentView;

public class PaymentController {
    public PaymentController(){
        // logic
    }
    
    public double countTotalTransaction (ArrayList<Order> transaction){
        double total = 0;
        for (Order order : transaction) {
           total += order.getOrderPrice(); 
        }
        return total;
    }

    public String getTransactionListAsString(ArrayList<Order> transaction) {
        StringBuilder sb = new StringBuilder();
        sb.append(" Transaction List :\n\n");

        for (Order order : transaction) {
             if (order instanceof RoomOrder) {
                RoomOrder roomOrder = (RoomOrder) order;
                sb.append(" ").append(roomOrder.getRoomType().getTypeName()).append(" x").append(roomOrder.getQuantity()).append(" : ").append(roomOrder.getOrderPrice()).append("\n");
             } else if (order instanceof FnBOrder) {
                FnBOrder fnbOrder = (FnBOrder) order;
                sb.append(" ").append(fnbOrder.getFood().getMenuName()).append(" x").append(fnbOrder.getQuantity()).append(" : ").append(fnbOrder.getOrderPrice()).append("\n");
             } else if (order instanceof LaundryOrder) {
                LaundryOrder laundryOrder = (LaundryOrder) order;
                sb.append(" ").append(laundryOrder.getLaundry().getLaundryName()).append(" x").append(laundryOrder.getQuantity()).append(" : ").append(laundryOrder.getOrderPrice()).append("\n");
             }
        }
 
        return sb.toString();
    }
}


//       public boolean createPesanan(ArrayList <Produk> listProduk, ArrayList <UkuranEnum> listUkuran, ArrayList <Integer> listJumlah,int metodeBayar,Alamat alamatChosen){
//         boolean berhasil;
//         DatabaseHandler conn = new DatabaseHandler();
//         conn.connect();
//         try{
//             for (int i = 0; i < listProduk.size(); i++) {
//                 Produk p = listProduk.get(i);
//                 int index = SingletonKeranjang.getInstance().getListProduk().indexOf(listProduk.get(i));
//                 int in;
//                 UkuranEnum ukuran = listUkuran.get(i);
//                 switch (ukuran) {
//                     case S:
//                         in = 0;
//                         break;
//                     case M:
//                         in = 1;
//                         break;
//                     case L:
//                         in = 2;
//                         break;
//                     default:
//                         in = 3;
//                         break;
//                 }
//                 PreparedStatement stat = conn.con.prepareStatement("INSERT INTO pesanan (`customer_id`,`alamat_id`,`barang_id`,`jumlah`,`ukuran`,"
//                         + "`warna`,`harga_total`,`biaya_pengiriman`,`jenis_pembayaran`,`status_pembayaran`,`status_pengiriman`) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
//                 stat.setInt(1, SingletonProfile.getInstance().getUser().getId());
//                 stat.setInt(2, alamatChosen.getId());
//                 stat.setInt(3, p.getId());
//                 stat.setInt(4, listJumlah.get(i));
//                 stat.setInt(5, in);
//                 stat.setString(6,p.getWarna());
//                 stat.setDouble(7,p.getHarga()*listJumlah.get(i));
//                 stat.setDouble(8,10000);
//                 stat.setInt(9,metodeBayar);
//                 if(metodeBayar == 1){
//                     stat.setInt(10,0);
//                 }else{
//                     stat.setInt(10,1);
//                 }
//                 stat.setInt(11,0);
//                 stat.executeUpdate();
//                 SingletonKeranjang.getInstance().getListProduk().remove(index);
//                 SingletonKeranjang.getInstance().getUkuran().remove(index);
//                 SingletonKeranjang.getInstance().getJumlah().remove(index);
//                 SingletonKeranjang.getInstance().removeLength();
//             }
//             JOptionPane.showMessageDialog(null, "Berhasil melakukan pembayaran");
//             berhasil = true;
//         }
//         catch (SQLException e){
//             e.printStackTrace();
//             berhasil = false;
//             JOptionPane.showMessageDialog(null, "Gagal melakukan pembayaran!!");
//         }
//         conn.disconnect();
//         return berhasil;
//     }
// }
