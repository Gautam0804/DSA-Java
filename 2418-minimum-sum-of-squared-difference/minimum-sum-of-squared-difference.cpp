#define ll long long 

class Solution {
public:
    long long minSumSquareDiff(vector<int>& A, vector<int>& B, int k1, int k2) {
        ll n = A.size() , k = k1 + k2;

        map<ll,ll> mp;

        for(ll i = 0; i < n; ++i) 
            mp[-abs(A[i] - B[i])]++;    

        while(k > 0) {
            auto it = mp.begin();
            int val = (*it).first , cnt = (*it).second;

            if(val == 0) 
                return 0;

            if(k > cnt) {
                k -= cnt;
                mp.erase(mp.begin());

                mp[val + 1] += cnt;

                if(val + 1 == 0) 
                   break;
            } else {
               mp[val] -= k;
               mp[val + 1] += k;
               break;
            }   
        }  

        ll ans = 0;

        for(auto &it : mp) 
           ans += (it.first) * (it.first) * (it.second);

        return ans;   
    }
};