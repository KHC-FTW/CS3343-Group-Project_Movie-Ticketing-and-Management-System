package release.command;

import release.exception.ExNoPaymentRecord;
import release.product.MovieTicket;
import release.product.Product;
import release.product.ProductWithPortion;
import release.record.PaymentRecord;
import release.user.Member;
import release.user.UserCenter;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CmdListPaymentRecords implements Command {
    private final UserCenter uc = UserCenter.getInstance();
    private final Member currentUser = (Member)uc.getCurrentUser();
    @Override
    public void execute(Scanner scanner) throws Exception {
        List<PaymentRecord> paymentRecords = currentUser.getPaymentRecords();
        try{
            System.out.print(PaymentRecord.showAllPaymentRecords(paymentRecords));
        }catch (ExNoPaymentRecord e){
            System.out.println(e.getMessage() + "\n");
        }

    }
}
