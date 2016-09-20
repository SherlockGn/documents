#include <iostream>
#include <string>
#include <vector>

using namespace std;

void printStringVector(vector<string> const &strvec) {
    for(vector<string>::const_iterator it = strvec.begin(); it != strvec.end(); it++)
        cout << "(" << it -> size() << ") " << *it << endl;
}

void split(string const &str, string const &spchar, vector<string> &result) {
    string cstr(str);
    int begin = cstr.find(spchar);
    while(begin >= 0) {
        result.push_back(cstr.substr(0, begin));
        cstr = cstr.substr(begin + spchar.size(), cstr.size() - begin - spchar.size());
        begin = cstr.find(spchar);
    }
    result.push_back(cstr);
}

void split(string const &str, const char* spchar, vector<string> &result) {
    string spstr(spchar);
    split(str, spstr, result);
}

int replaceAll(string &str, string const &p, string const &t) {
    int begin = str.find(p);
    while(begin >= 0) {
        str.replace(begin, p.size(), t);
        begin = str.find(p, begin + t.size());
    }
}

int replaceAll(string &str, const char *p, const char *t) {
    string pstr(p), tstr(t);
    replaceAll(str, pstr, tstr);
}

// overflow is not considered
int parseInt(string const &str, bool *success = NULL) {
    bool minus = false;
    int start = 0, result = 0;
    if(str.at(0) == '-')
        minus = true, start = 1;
    for(int i = start; i < str.size(); i++) {
        if('0' <= str.at(i) && '9' >= str.at(i))
            result = result * 10 + (str.at(i) - '0');
        else {
            if(!success) *success = false;
            return -1;
        }
    }
    return minus? -result: result;
}

int parseInt(const char *cstr, bool *success = NULL) {
    string str(cstr);
    return parseInt(str, success);
}

int main() {
    string str("1, 3, 4 ,123,  4,-05");
    replaceAll(str, " ", "");
    vector<string> strVector;
    vector<int> numVector;
    split(str, ",", strVector);
    for(vector<string>::iterator it = strVector.begin(); it != strVector.end(); it++) {
        numVector.push_back(parseInt(*it));
    }
    for(vector<int>::iterator it = numVector.begin(); it != numVector.end(); it++)
        cout << *it << endl;
    
}