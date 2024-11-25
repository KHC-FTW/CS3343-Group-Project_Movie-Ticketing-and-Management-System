package release.helper;

import release.exception.*;

import java.util.Arrays;
import java.util.List;

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

    // newly added

    public String checkInputDynamic(String input, final String type, final int requiredLength) throws ExInvalidInputLength {
        if (input.isEmpty() || input.length() > requiredLength)
            throw new ExInvalidInputLength(type, requiredLength);
        return input;
    }

    public int checkDuration(String part) throws ExInvalidDuration {
        try {
            int duration = Integer.parseInt(part);
            if (duration < 1 || duration > 300)
                throw new ExInvalidDuration();
            return duration;
        } catch (NumberFormatException e) {
            throw new ExInvalidDuration("The value you entered is not a valid number for duration.");
        }
    }

    public String checkGenre(String genre) throws ExInvalidGenre {
        List<String> validGenres = Arrays.asList(
                "Action", "Thriller", "Comedy", "Romance",
                "Horror", "Family", "Cartoon", "Sci-fi",
                "Drama", "Documentary", "Fantasy", "Adventure");
        if (!validGenres.contains(genre)) {
            throw new ExInvalidGenre();
        }
        return genre;
    }

    public double checkPrice(String price) throws ExInvalidPrice {
        try {
            double priceDouble = Double.parseDouble(price);
            if (priceDouble < 0 || priceDouble > 500)
                throw new ExInvalidPrice();
            return priceDouble;
        } catch (NumberFormatException e) {
            throw new ExInvalidPrice("The value you entered is not a valid format for price.");
        }
    }

    public void checkPopularityScore(String part) throws ExInvalidPopularityScore {
        try {
            double rating = Double.parseDouble(part);
            if (rating < 0 || rating > 10)
                throw new ExInvalidPopularityScore();
        } catch (NumberFormatException e) {
            throw new ExInvalidPopularityScore("The value you entered is not a valid format for popularity score.");
        }
    }

    public void checkClassification(String part) throws ExInvalidClassification {
        List<String> validClassifications = Arrays.asList("I", "IIA", "IIB", "III");
        if (!validClassifications.contains(part))
            throw new ExInvalidClassification();
    }

    public void checkLanguage(String part) throws ExInvalidLanguage {
        List<String> validLanguages = Arrays.asList("English", "Cantonese", "Putonghua", "Japanese", "Korean", "French", "Spanish");
        if (!validLanguages.contains(part))
            throw new ExInvalidLanguage();
    }

    public void checkSubtitles(String part) throws ExInvalidSubtitles {
        List<String> validSubtitles = Arrays.asList("Chinese", "English");
        if (!validSubtitles.contains(part))
            throw new ExInvalidSubtitles();
    }
}