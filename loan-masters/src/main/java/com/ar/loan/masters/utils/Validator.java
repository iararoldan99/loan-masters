package com.ar.loan.masters.utils;

import com.ar.loan.masters.dto.ClientDTO;

import java.util.function.Function;

import static com.ar.loan.masters.utils.Validator.ValidationResult.*;

public interface Validator extends Function<ClientDTO, Validator.ValidationResult> {

    static Validator hasBadCreditScore(){
        return client -> client.getCreditScore() >= 10 ? SUCCESS : BAD_CREDIT_SCORE;
    }

    static Validator isUnemployed(){
        return client -> client.getJobTitle().equals("Unemployed") ? UNEMPLOYED : SUCCESS;
    }

    static Validator isDebtor(){
        return client -> client.getTotalLoansTaken() > 5 ? DEBTOR : SUCCESS;
    }

    static Validator hasCriminalRecord(){
        return client -> client.isCriminal() ? CRIMINAL_RECORD : SUCCESS;
    }

    static Validator isUnderage(){
        return client -> client.getAge() > 18 ? SUCCESS : UNDERAGE;
    }

    static Validator isBlackListed(){
        return client -> client.isBlackListed() ? BLACK_LISTED : SUCCESS;
    }

    default Validator or(Validator other){
        return client -> {
            ValidationResult result = this.apply(client);
            return result.equals(SUCCESS) ? other.apply(client) : result;
        };
    }
    enum ValidationResult {
        SUCCESS,
        BAD_CREDIT_SCORE,
        UNDERAGE,
        UNEMPLOYED,
        DEBTOR,
        CRIMINAL_RECORD,
        BLACK_LISTED
    }
}
