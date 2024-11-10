package user_in_out.exception;

public class FormatChecker {
    public static FormatChecker instance = new FormatChecker();

    public static FormatChecker getInstance() { return instance; }

    public String checkType(String type) throws ExInvalidUserType {
        String typeLow = type.toLowerCase();
        if (!typeLow.equals("member") && !typeLow.equals("admin"))
            throw new ExInvalidUserType();
            
        return typeLow;
    }

    public String checkName(String name) throws ExInvalidName {
        if (name.length() == 0 || name.length() > 12)
            throw new ExInvalidName();

        return name;
    }

    public String checkUsername(String username) throws ExInvalidUsername {
        if (username.contains(" ") || username.length() == 0 || username.length() > 12)
            throw new ExInvalidUsername();

        return username;
    }

    public String checkPassword(String password) throws ExInvalidPassword {
        if (password.contains(" ") || password.length() == 0 || password.length() > 15)
            throw new ExInvalidPassword();

        return password;
    }

    public int checkAge(String age) throws ExInvalidAge {
        try {
            int ageInt = Integer.parseInt(age);
            if (ageInt < 1 || ageInt > 125)
                throw new ExInvalidAge();
    
            return ageInt;
        } catch (NumberFormatException e) {
            throw new ExInvalidAge();
        }
    }

    public String checkSchool(String school) throws ExInvalidSchool {
        if (school.equals(""))
            throw new ExInvalidSchool();

        return school;
    }

    public String checkSearch(String searchText) throws ExInvalidSearch {
        if (searchText.length() == 0 || searchText.length() > 12)
            throw new ExInvalidSearch();

        return searchText;
    }
}