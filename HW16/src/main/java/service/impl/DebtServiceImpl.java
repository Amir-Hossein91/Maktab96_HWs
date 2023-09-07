package service.impl;

import basics.service.impl.BaseServiceImpl;
import entity.Debt;
import entity.Loan;
import repository.impl.DebtRepositoryImpl;
import service.DebtService;

import java.time.LocalDate;
import java.util.*;

public class DebtServiceImpl extends BaseServiceImpl<DebtRepositoryImpl, Debt> implements DebtService {
    public DebtServiceImpl(DebtRepositoryImpl repository) {
        super(repository);
    }

    @Override
    public List<Debt> calculateDebts(Loan loan) {
        List<Debt> debts = new ArrayList<>();
        for(int i = 1; i <= 5; i++){
            if(i != 5){
                for (int j = 1; j <= 12; j++){
                    Debt debt = new Debt(loan);
                    // 0.00279 = (amount * 1.04) / 372
                    debt.setAmount(Math.pow(2,i-1)*0.00279 * loan.getAmount());
                    int monthNumber = 12 *(i-1) + j;
                    debt.setDueDate(calculateDueDate(loan,monthNumber));
                    debts.add(debt);
                }
            }
            else {
                for (int j = 1; j<=11; j++){
                    Debt debt = new Debt(loan);
                    debt.setAmount(Math.pow(2,i-1)*0.00279 * loan.getAmount());
                    int monthNumber = 12 *(i-1) + j;
                    debt.setDueDate(calculateDueDate(loan,monthNumber));
                    debts.add(debt);
                }
                Debt finalDebt = new Debt(loan);
                finalDebt.setAmount(1.04* loan.getAmount()-debts.stream().map(Debt::getAmount).reduce(0d, Double::sum));
                finalDebt.setDueDate(calculateDueDate(loan,60));
                debts.add(finalDebt);
            }
        }
        return debts;
    }

    private Date calculateDueDate(Loan loan, int monthNumber){
        int year = loan.getBorrower().getGraduateYear()+1;
        Date date = loan.getRegistrationDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        year += monthNumber/12;
        int month;
        if(monthNumber % 12 > 0)
            month = monthNumber % 12;
        else{
            month = 12;
            year -= 1;
        }
        return new GregorianCalendar(year,month-1,day).getTime();
    }
}
