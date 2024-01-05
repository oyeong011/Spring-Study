package hello.typeconverter.converter;

import hello.typeconverter.type.IpPort;
import org.springframework.core.convert.converter.Converter;

public class IpPortToStringConverter implements Converter<IpPort, String> {

    @Override
    public String convert(IpPort source) {
        // IpPort 객체를 String으로 변환
        return source.getIp() + ":" + source.getPort();
    }
}
