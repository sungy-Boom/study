from xml.sax import handler, make_parser

paperTag = ('article', 'inproceedings', 'proceedings', 'book', 'incollection', 'phdthesis', 'mastersthesis', 'www')


class mHandler(handler.ContentHandler):
    def __init__(self):
        pass

    def startDocument(self):
        print('Document Start')

    def endDocument(self):
        print('Document End')

    def startElement(self, name, attrs):
        print('Element start')

    def endElement(self, name):
        print('Element end')

    def characters(self, content):
        print(content)


def parserDblpXml():
    handler = mHandler()
    parser = make_parser()
    parser.setContentHandler(handler)
    f = open("E:\\project\\java\\study\\xml\\naiveBayes.txt", 'r')
    parser.parse(f)
    f.close()
    if __name__ == '__name__':
        parserDblpXml()

parserDblpXml()