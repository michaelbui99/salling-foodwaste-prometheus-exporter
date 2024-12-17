package dk.michaelbui.salling.foodwaste.prometheus.exporter.os;

public class EnvironmentVariableReaderImpl  implements EnvironmentVariableReader{
    @Override
    public String read(String environmentVariable, String defaultValue) {
        try{
            String val = System.getenv(environmentVariable);
            if (val == null){
                return val = defaultValue;
            }
            return val;
        }catch (Exception e){
            return defaultValue;
        }
    }
}
