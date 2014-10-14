package io.gmi.chartms;

/**
 * Created by gmead on 10/14/14.
 */
public class TestUtils {

  public static final String CHART = "{\n" +
      "        chart: {\n" +
      "            type: 'bar'\n" +
      "        },\n" +
      "        title: {\n" +
      "            text: ''\n" +
      "        },\n" +
      "        xAxis: {\n" +
      "            categories: ['Apples', 'Bananas', 'Oranges']\n" +
      "        },\n" +
      "        yAxis: {\n" +
      "            title: {\n" +
      "                text: ''\n" +
      "            }\n" +
      "        },\n" +
      "        series: [{\n" +
      "            name: 'Jane',\n" +
      "            data: [1, 0, 4]\n" +
      "        }, {\n" +
      "            name: 'John',\n" +
      "            data: [5, 7, 3]\n" +
      "        }]\n" +
      "    }";

  public static final String HTML = "<div class=\"container\">\n" +
      "    <div id=\"test\" style='width:1045px;float:left;border-style:solid; border-width:1px;'>\n" +
      "        <div class=\"row\">\n" +
      "            <!-- TEST IMAGE only-->\n" +
      "            <div class=\"col-md-3\"><img style=\"margin: 15px; \"height=\"100\" width=\"100\" src=\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAANQAAAEsCAYAAAC7aJ3YAAAABHNCSVQICAgIfAhkiAAAAAlwSFlzAAAD8gAAA/IBpxuJTQAAABl0RVh0U29mdHdhcmUAd3d3Lmlua3NjYXBlLm9yZ5vuPBoAACN7SURBVHja7Z0HmBRVtsdvVYeJDDDDACIZQQaQjAqyhCHDMICAZMGMrK6KYHwqZj/Upy7m5RnYJwZEd32KrmLCBIjKYlhBxAASJUqawNx3Tnc19PR0rO6u7qr6z/f9vg4z1NB3zq9uOvdeIaUUAIDEgEIAAEIBAKEAgFAAAAgFAIQCAEIBACAUABAKAAgFAIQCAEAoACAUABAKAAChAIBQAEAoACAUAABCAQChAIBQAAAIBQCEAgBCAQChAAAQCgAIBQCEAgBAKAAgFAAQCgAAoQCAUABAKAAgFAAAQgEAoQCAUAAACAUAhAIAQgEAoQAAEAoACAUAhAIAQCgAIBQAEAoACAUAgFAAQCgAIBQAAEIBAKHszsiRI9sQZxMziZuJBcSLxPvEN8ROYi+xgfiYeIV4griduJyYSPQgHChPCGW+ghTCRQwg5hGD+HWMAjUmziWeJTYTMkHsJ14jriA66PxseURJpkPc5VTEs/w58TeHUElDFWKKqogjFGjSB73+gx77hxHIQYwiHiXWJ1CgSOwgnicuIPKikKmpWxVb/T8bQ+9tpMcz8PeHUImVSRXnUmBVKQEBp3g5Rs9nBIiUq9UWPxkoUbja6z6iSQiZujpVcTBQJt/ncyhiPz1viDiAUIlq5p0ZTCb/oOPvEx0oaBsR92j9HplmVBDPEV38PltjqmUPhvpsfjXVl/SIfhqEih+3Q3wkIgQc06ZVi00UrOVpKFIw3h1GX/T//iSaz6ZxMeIBQsVbO3UOFlyDCgpktkP1PHfQY4d2RbJ0UF9pEpk8DB8+XBbWKygP0oSVWapa4zNnOsQyxASEiq/vpIj7AgOrOD9frjzjDLmkV0/Zp3NbOXToUG+QDh9iKqGYyZMmyoYNGlT7fHOaN5fv9+ghW2VnSaV6s283YgJCxdfcU8XywP7FX9u2lYv7niWvH9NbzhjRv3qQmkiqUaNGySkkFD82bdrU89maZ2fIT+lmwTeMa0isILVzIeICQunGqYotgU2iuwecIf88up8nKC8sKa4epEMGmEao0aNHy0kTJ3iejxkzRp7evbt8/MweHpmYe9u0CSZUb8QFhNINCVThH1Ct6tWWU0pP1EIXj6xeQ5mpH8USTdaE8jT/6PmFEybIhcXFHqEWtm8fTKgixAWEiqcPtddXM2W6XXLyoF7VgjKwyVc6dICpmnyTJ50Qavz4cXLs2LFyGkn12MCBcmnnzsGEKkBcQCjdOBSxjgOpVk6W7Ne3rxxHwoweOcLbZCotkWOHDw7oQw0z1aDE1MkTqwk2YYJXsBkTJ8r7Bw8OnG/juTYFcQGhdONyuZa3bt5YjhjhlWhkyQg5s9RbK11SGlg7DTLdKB8LVVpaevz1uVOmHH997qRJskFhveNCORWxDTEBoeLJBK87ZMiQdaWD+lUXZ8QwOXlYsRw5Ynj1JtTA3qYT6pxx4+TECeccfz2OXk8lqbjpx48l9BkzMzN9Ut2JuIBQ8Qj1skegYYMjN+VIMhbNbEIx06ZM8jT3/Jt+48ePP15TDRs2TBYU5MuGDRv2R1xAKL0yjalW+xT3Ch2UJSVUO/3JlDJ5RvtGj6LaaHLIgYtpJ5qB9yE2IJQemeoQW2sE1+B+nj5UoExmSzkKxrhxYz39qQlUM/nem3DOOXLa1Cny7LPP9r1XyYsZESMQKlahFoYcah5CfaeBfeUokogFKx060PQy+cMTvdOotpo2eZKcNGFCsJ/5mnBBKIgSrUzFVhIkSdwCoSBLNDK5iI0QJiJlxKkQCkQSajRkiZoHIBSIJNQ/DBuqnjZNzp8/PynMmDHDqD0rnBAKhJKp0MjVtg899JAM/Fq5cqV87bXXZHl5uQz1VVlZKZcvXy5XrVoV8mcWLlxoVC1VAqFAKKGuNLrZ9Oqrr8qysrLjIlRVVckHLx0l77r6Ennw4MEaovD375t3nVz4l1JZUVFR4/v83rJly6qlEiWZJRAKhBJqbUpy6aZOlYsXL5br16+XmzZtkqtXr5aRvr799lsPPol++OEHuWTJEjl9+nSj//9HOT0LQoFAmTqny4K/q666Si5YsEA+++yz8uWXX/bUOCtWrPDw5ptvyqVLl3q+9/DDD8urr77af9I1VcyEUCBQqCswaqeb5yEUCBTqfoihm08gFAiaVQ50sQVCmXufPEcShFoNMXRzzI65fVaRqbtLiJ/psVWChdoBMeKiBYQyn0ynZQmxp6UQVZlCbKPXjRIkUyaEiJu+EMpcMp1KMv1+AX2MZkQ+SZUhxK/0fn4ChGoNIeJmGoQyj0wtqEbaOZ0+wjziJG3DkHremuobep4Tp1AnQ4i4GQuhzCFTY27eTSZ5WKZrvbWT96QLop8QR+j7n9FrdxxCqSY6MSNd6Q6h0l+mBiTL5vFCHJun1U5cS+VqQnXU3ushxCH6ubfiGf2jgPgRUsRFIYRKb5lUtxDrhwhR6ZOJGcpHcGpC9dHeu4VoKsRhkmpxHEK9Dyl0cxjzUGbYYFKIC6l5d+A6P6G6azLR92Sp3/vtiTpClGUJsUCnUM9ADN18D6FMAtVS1zUkqW7UxDlVE4qbfRf5CdWczy7yvn+UHm/WIdRtEEM3b0MoE5EhxP0kzIGbtCFz4a2N5PV+QjXU3m9HFAqxn2qwWTEKNR1i6OYxCGUyqH+0qK0Qf9TXxCn0k+kGTTBfv+oa7+v9DiEmxiBUvrbxCASJnUEQynwjfir1j5bVFaKSByVO9hPqPCJba/KN096bzadlkFROIYYiQTa5ibE87QChzCmVi2qqjwtIquZ+Qg3Wzm4igeRVfu9z849+/g/6d8OjFKoEgsTMPVgCb26psqmmWtfBbzi9i9bcK9CG0Of5ZVTkEdQH20/f7xiFUE5iOySJiSIIZX6p6pAkP5YKUcHitNaE8m8GXqf1qzirYoa3ptoVTYY6b4YPSaJmNfbls45UDUmqrROEqGriO/vWT6jx3ppJNtJeT/Hm/f0WKUOdgqSD0YFZUlIiBwwYIHv27Ck7deokW7duLVu1aiVbn9JKFrVpJdu3bS07dmgvO3fuJLt16yZ79OghzzzzTNm3b8oPKLgMQllLKk6a3eXL7evsJ1RP7b1T/N4bSc1EkmxLpAx1CpQXkhmIgwYNkl27dpXNmzWTebk5UlFEsHNso8LldMiTCvNlh6K2HsFYToNk4lStbAhlPak6uIU4kKMNTvjk8TUDu/u9N5Go662pvg2XoU6BUp/YncgA7NevH9U8p8jsrMzAM2t1yxTsGi6HKhsW1JHt2xXJPn36JFOwATgswKofTIgzSaqDY/3k4f6Ukxjl914fLfjqe2uqL8NlqFPAzIg36AYPHiyLiopkXk52QgWKhawMtzyNmovDhw9PpEwLsQ+JxTdpoa/BVEsduJI+5s3aYERewDB6Wy3IeHi9g3fZR9gMdQqcd/Q26Vo0by5VVUmJRMFqLzc1DYvatJZDhw6NVyY+hK4OhLLBrkcOISbUJqmm0UfN8dZE0j9TvakWXA2016cJ8Ue4DHUKnJbEoWiDrbi4WDZrfLJUFSVlAkXC6VBk6xZNPbWnTqHGQCYbbSPmEuJSqoGO5GgJs/P8lnjU81uYOE+ryaivtT9chno0+53zgc4tW7SIa3DBaLj2bNGsiRw4MKbTF1+ASDbcl4/6UzdRc++I/6jfpVpTz6GtqfK9zwm3TUgqX4Y6x1oQqeaHCrIuXbrIDJfTNCLVEItq0w7Ux4omo5w3s4FINt3oMkOIv54sxKGbAhYm1tGSZ+cFJNdS83CfU9SdLUTFKh7LiLSzLDfv8uvUTnkfKVF9LB56D9O/Wk5kQSKb7xzL/aN2Qhzk5l4nLXAaB8jkt3DxkEv03SfE9nIhjhFybBCpHuQA6969O/VFVNNKFHJE0O2UZ/XqFSjTe3afb4JQfjvM8kheN5KlRcA+FP70FuKwW3Q5IMSPR4R4lfpCFZW1a/80Pcj1MhrWL/zG7LVSuNqK+4GcnaHNX30ImSBUoARukmplbap2OBVpQoBMxUIczRBFJNMmkmkpy1QxbeKbH8+atfjdgQMv6eB3nTynKv4tLChSMPLzcv5o0KBBS4gDoYJJlUMyfVdAUt1UfcOXsgzR4qAQG0mmJSRTecWfznpw1R23//2HJ554Qs6Zs3jlkCFX96N/X8vtEN/ZRSYfDkVQ2YiBkAdCBZMq3y3E5lHaso8SISoyRCMKmA1HhXiRa6by0aPPWzP78rkfskw+Zs9esjYv8+Z1dpPpeBOQbkI8aQ6BIFQwqRpRTbX9dE/qUeEhIdaTTM/TNyrLS0ou/vzaa+vuv+v2p77wyfTQQ08f7tRu7RanspuEy7FkvykaqVRFVBJ9IBGECiJVbhunaLWbWoBHOUmCKqqy4cMv+3zu3Po7Zl929XsPP7yw4rhMRV9uV5VyT8k5lOekHWspn1TU/KtwqeJ0xBCEChBKziWqhHiZXpSVde/2zKo/z7z5o1tvWbT2kUf+VsUyLViwsKxju7XbfDL5cChX2loqpyLKqKbqgDiCUP5C5QpR/hmXRv8/fbzm9tue2+jfZ3r88Sdlr9M//41aOdVk8lJJUnW2t1SqOJTos7kglPml4k2Spg0efEWfuXOf+9RfqNElyzc4lENBZPKiKt9RYLltK5VnzZUq9vIhDhAKMtVg2LDprWbNWvTmY4/9rWrmRa+sy3Dsqgolkw+nOt/WQmlS8YF39SEUqEFh4d879up539s57s2VkWQ60Z/qY3upqPm3LhnnHUMo8w+n36kquccyHG9Jt+P3qIRSlV+IAlsOpQfUVDdDKOAvUzfhnbwkQbKIz2W281eZ6dgWUSqXupJ+Ptu2Umm5f1x2RRAKePL8nIr4sfp8S116vtVTWlnOzSTWVnr/WBiplpNULlvXVJkO8bMdm36QKHDJvCLuCRYgbrUVPR45Lg1LleX8jcQ5GnyQQvkHCWVvqfJc4r8hlL1rp7ZanlqIlawj6bF6zZTh2OURy6n+UXOQQn2b/k2ObYVSFVFFtIdQNhSJWEQyHY4cKJeFaObt9zQH3eqeAOHWUm12mp2bfpvs1PSzs0RZxOXESmJHbIFyT5ih8yMym8TKcOzwe+8wvb6Omn+qLZuALlXMh1DWFakr8TLxA1Ghf77lsbCjfYpSqY0Mbj3+nlt9l+RqYtfaahUxyuq1lV0kyiOuJ74g9iRmeJhqG+WtqOanuCmY5dwivbmAB0nG8+hRsaNUR4j1xLPEqRDKfCL1Jl4nfhJhBhv0L7TLoOdfyWgzKbwjg5s9zUJV+YzoaOcJ4F3aDW4u3/AgVPpKVIe4lVgrvIeqJXmhHc9R/Ry1VJ6BCnWXRyyXupdqq3vpOjl2FotvdDxn9S9iKB/zCqHSQ6S+xDLiF6LK2OwAnqPaHZNUvpFB7me51PUk5jjT7+eXAPio1v8QjxBNIZTxEtUl7iA4IfNAaudcutPj4Zil8uYA8sjgL9QUfEOTUwAhthKfEtPDnYgCoRLXN+JTMn5Nqzw2paTGxG8s8KBFtnMjCXa77ddX+VGmjcg+z+d+QajEy3R67PNGRjb/zqfHKt1S+ch2fkSchmZgdX4X3jO85vBGIBAqfpmyibTfC08VFyREKs+Qu2OBdKq1IVPwgYzHIFR8Qr1k9ICD/poqcVJRX106lUskD4ChxqoG95svhlD6ZJpE7DXXH/y8BErFbJMO5U7pUpum7DjRNIT70S0gVGwyNSQ2mTPjekpcAxWhRwZXUR9rOgnVAnJ5J4idECo6mShOxIoEFv5h4//gM5Ii1YmRwf8jwa4hmtpVqHLicQgVnVDzEiQBX2OtUxEp2pP83KRJ5SPH9SM1CVeSZNdKRcm3m1Q7iSEQKrxMpxG/xVnQFdrs+yw+y0lVPPMaKfqjT026VN4hd07G/YFqrHtIrrQXa3MCr8WJt/kQKrhMGVoGRDwFzEmxd/K1tGsWpz6Apnh2m022VN7FjTtJru+0ieI66bqql1sgixNw4/TxDoQKLtSzcWaIs0w9/K/pUj05YmkQSBMNk+pEziDvbsti5abVDkluh1in/b1baLmYOxOQD3gDhKouU6k2K663UHnSr2fgdd1qwu6CCUhTGmuoVN6Vw0epKfi1VMU12tKTtCgLnlcs9Pvbd0lAy4SH0jtCKO0QNGJjHIXJ2ea9g1y3afqdBsibvlQYKpV3ZPAYibWGHlmsvHQoi8lBFoR+Gec1+ajWTAglxPI470x9Q1x3ZnpuXVxMj/sMl+rEAMZ/qB+ziJhEgteq0SQzpAyUmkPemlRfxHHdSu6b2Voo+ppNHIxjtKg4zH57S9M3TekkevwkZVJ5cwa3ktwHSKyV1Oe6lp4PI3pQsLeg9/KOpz2F6w/p7kep4vsQ8VArTql2Cx4FsqNQ9NWa2BKHTIPCXd+legYp0nb7Ym/A3mbIsHo4eB/3LM+ix8Bak1Oo9tD/lWu0dz2nN/KJI07lL/R8HL3Xi/qFzaQIsexECf/5j4VqnmlSrYmjfHkX4JPtKNRnOguMJRwa4douJcF7SiSPPxGbUyqVd2TwgGfRY7QHJVRnB8n2NvEoMYekO5uk60rSFdL3XCE+94CRYf5+ucTncZTrJ0YtrU+nZRk/65RpRBTX72CuDfe5ifVCghNr9eYMHiWxfo7qoIToOaTdNNbS9d+UGY7H6Wa3dBZ9r3cEqVbHsdvSfXYSiveDOBRjIfEQeGmU159othMsvJOep9DjU3zub8rF4pFBFou3Q0u06ApdL8/93cwo/o452sakepbwfG8noe6NsXD4pLwx0V7fqYr7zH1ETD16vNvTh0m1WN6RwV896U2BB3jHQ23314ujjJUcrXsQq1Q7jdgLMF2Eei/GwlkXy/XdDvG+FZY28GkeTnUSPV+dFmLxPoMsV7jzh6Ml17Xxyxi7CJ/qkOovdhEq1mXtn8RyfaeSvntQ6JeriB4fIHalXCy3Y7enOVhzZDCWTPmNP+vod2+Isdxet7xQ2nB5rPlbb8ZwfYdVTwr0bQntVLrS8xuJf3mWy6dKLJ7HyiGx+IifmIVy/rRbR+ysjLHcvrKDULN1BNTfYkxlsvwxnCdeq1RTtKHgnkHv/w+9/jUFI4NlVGP9VO2ghMgTy78d5o8SY+y8o2NUuIHVhXpTRxDdEsP1W9p9MxPvaOFc4gODJ46rqCn3M8myJewRqlp/rJIPPYwxdp7WsVvSeVYXSk9m8eUxHkBta6GUagm5uTLLWeI5stTIxNwTI4NlodZuVdFj8xhjZ46O8njN6kJt0FEo02K4/gBstxWq5sqmpuF4ev6GYXNdPEHMcjmVgzX6X4pS0S3G2NEzv7jO6kLFOsLHQ6UlMVx/LOSJRq5MTa4PDMnQcKu7PalNfAKJ74C6LOeWkhhjZ6C2QUusKxJyrSzU1zoC4OkYrn8BhIl1nVZ9CvTZ9PisJwlWVV6SPOLMg6u8wlyI94gPtQz5lcQa7Zysr4nviA3EJm1AhAcmdmqT0vu1tKOy4305PuzbO+S+X+Y4N10eY+ycruPIIk6kzrGyUF/pzCCuG+X1r4Yk6Qqf4ujwTFgrVEMqonBHjLHTk9ino4bKsLJQH+pcNv1QlNe/HYFrlman2BZj7LymcxGqamWhXtL5B+DBjOxI16c/0m0IVtMItTWGuDlZ5xZkP2BiN/Scwrworj8XwWqWvptYH0PcLNL5e1ZbXaieOpZu+Pg20p7W9HUpgtUcc2VORayMMmZqadvE6fld/7K6ULXj2D30KHFJhOufi4A1jVDLooyZu+I41uhhO2SbfxuqADJUVboURZ6ckeF5dKtqTAmPmIcyVZNvUZTJznoP3WMJJ9lBqA+CFQDL0zUvT77VrZtcecYZ8v0ePeRZdep4JPP7uf3hVu7yBvIIVtPUUvdHESvnay0TPxEVmeVwyExVjfQ7diX7nN50EeqB4HcsRb6tyeTjA5IqhwovyGRdrTAHXCNgzcGNUcTK59X39FNk//x8edspp8hLmzSRtSg2VEUJN3/ptoNQQfeUaETNPH+ZfHTLy6tWi7FgVGv9EOLanRGopuGiKOJkr393YHqjRtVi46VOnTxdgxDXX2WXFbtqsBQkLrAPqUYKFOqU7OzjP8PV/K10d6JHHkYfFuTazRGopqFPmBhpRzfPA04/Wbgmerd79xrx0bdu3VDXf9lO+/Jdr22fW02oqQF3oEeKiqpV6SzUVc2ayRGFhdzhvDdYJ1YJuC5IW0Iu/qM+0preAaJwyyRYC2ZIQUGwa/OOxOPtJFQmb/UUWBBcfZ9Ru7a8gqQpKSz09KsCf6a+2y2nkXhuRVkSYtfYzQjWtB/hOxru4D2qmY4937FjQGaFIvk9f5lWUIumltMZ7Hd8Y8Rml+m2t/lDgTJxH4nnKLK04XNfzeUvlsvb3OPnFwe7bqYj5l2VgMFzUPQ32hQmLs5unJlZPr9Nm2r9I27+NcvMlC9qUi3r2tVz8w0ytcLx8V92PCyggNjO8viGxnkAguVpSgVX6D6xb/aUk07yHz7neaxpgtOXg20jpooHEbjpTa5LvB0mLqbVc7mOUgxUKQGjwAzXVDna6J47+ND5xmjyPq16nM3KU3Ny5KymTT13Ll+fqX1urpzXqpWnwLgQn2zXzicU331ui3DN6Qja9CbHWbP/6388LNVMP1ONtM+/9ZKtTZ+4tFaMqkmm+r2n/fxCO58PVUyFUnVh48aeAYcbW7aUdahNXJtY3r378YGIm0iurt7h8z18llCEa56JoE37Zt/ZUewYywNXx/imek7DhrIJtVr43+ZRbFxBN2AWbAT1s/l9vtmOqV+fb8C8r3lDWx8JSoXxBBVwlaIon1Izr7Ke213FhXhP69ZcQDyat52q/8qSwsJj0RyqxYsREbRpT7soN7fcxDXPC9RvYoG4JrqIbr4XEyzRwvbtZS69fzr1pcY2aMA12SYcWu0tvJN45I8Eeoaqet759VF6rKQC4kOJm9Lz/W7vYMSV0VzPocS8VBoYR1W0GQy8/QH97asWUFeAByW4tXI33WhbZ2d7mnhXN2/uuekWuFyV9P0K+vmzIFSQdH3tsRdxjva8MRXgK9HuD+BSxRsI3PTEqca0sLB/jsNx5PS8vAoS5g2S59CA/HyW6xjdbMv4UVXVG7UR45E4BT55Ul6E4E1P3GrkLHP/+UqSZjfVTLxiuzHv0Uiv92pbipVEe8QRhIpfqGYI3rRleIx/y16+JiIvMI00KAWhkgTdCXcheNNuH4nKUOfrmvLGbSehMh3i7wjitJt/Wm2plpCdhKKvUQjitOs/XQahzCtUnqJ/LwKQHBpCKHP3o9YpCOJ0SYj9xXI3bbsJpWCfvrQh2xl5DwkIlf7NvkLFm1CLoE49nSGUFZp9jpiPkgSJzo5QxB5L3rDtKBR9jUBQp3yF7i0QyjpCOegPug+BnbLJ3IpojyOCUCaBhLoXwZ2yydznLHuztqtQWrY6BieMHy7necCmEMqac1KLEOSG7x3xnqVv1HYWiu+UqKUMpyOEsnYttRhBbgxZTvGF5W/SdheKvloiv88w+kMoe9RSLyHYk5u3R2W80RY3aAjl3RBGVUQZgj81BwFAKAviVMQcBH1yyHRYd94JQoXJnnCpngO5IEECm3pUpvtDHYYHoawv1RkQIcErch2p3YUIQqV+gAL7TiRuEne57W7KkKjmdr/UTNkKIeJr6jlVcZie50MowFK117a3giD6d4OdZMvYgUAht26eBTH0QWX3lm1vxpAn7D5+r0OQmGX6zY5NPQgVXdMvlwJkO0SJeoicD4ZuYuuYgTiRc/2cijiIrcfCy+RQRDn1O0+zfbxAmqik6kzBUg6pgstEZXOMaqf+iBUIFct+fsUKBQ6kCppaNAkxAqH07EMxwXdXhkjH1zjNQWxAKL1Nvy7EQc/5v5CJm3qfIC4gVDxCfSa8S+b3U/OvQrFpn0l7zstddhL1EBsQSo9MfNzkHr/g2me3NVTKiV2LfJ+b99d7CvEBofQI9Z8gQbafpDpiI6GOiZqb2vCpkC0RIxAqFplmEntDBNphCrT9NpCpIsT3uMZ6C3ECoaKViWJJbIkQcHzX3svD6hbtLx2N8LO/E90RLxAqGqHuFNHXQNyvOmQhqaqikMnHV4gXCBVJphxtJCuWICzThtbNXlsdjfHnd/OpJhAK4oQT6imWQ2dA7lO8TSFTTQQr3htCuc5/v4lQIRQIJtPJ2l03ngDlWmq3S43YB0t5P8mpeJq1B+K8Hl/jIggFggn1ut98S7zwWVTj3A6xwrdLbbrUWiT7L/T4DJGovt82IgtCgcAUoz0JDNzNftdulOkQd7vVhF4/1tMDj9L/4Z/0vJS3TyMm6OgzhYLFnAehgL9Qn4rEDip8FGI4vh+JtVRRqvdZlMQPe3NNdCDbKZ6n58WEM+D/0k7H4Es4+FoFEAr4zt/dk+Ch5yci/E4X0ZXkujLHKd7IcHiaTVVB0n6CEmajlM10zQcjzRFpv39HAj8z3yAWQigQKsUoHjjDYo6O/0ctrsFURVxHTbR/Ug2zgvpgn1FT7XOqadYS/6bna4gVxGJuRpJAF9C/GUgUEXkx/r5fEvy5OSWpBYSyt0wXh0kx0guPFHY1wWd/LwmTwssglL1TjDYnYRCAhVJM8PnvTsJn53m4bhDKnkLdoQ1vJzqofjLJ5x+VwKFzf76EUDbcfjnBo1z+vGuSMmiV4IEJHzzAMxxC2UuohXGkGIWDt3NeYJIyUJMklK1SkiCTEI0SPEwe2H/6s4nKYlOSyoFTki6EUPYQ6p8JTDEKJlQbE5XFG0nM0OC5tUwIZfENLJNYO3mEMll53JTEsuABj1sglLWF+lgkd93SBpOVx9AEZJxHmuwtgFDWlGl4kmsnabaJTfpqnMSBCd/iyychlDWF+jbJMnHw3GvCckn2aSM82dscQllLpouSkGIULHDOM2HZrDdgn4o3IBRSjPRMaJ5kwvJZakDZ/G6G/EYIFV3A3CqM2Udvl0nL56rApSNJYg2EQopRLHxj0jLqZ0Bz2DdHNwxCmVuoJ5OUYhSMV0xaRoVJHunz50erpSQhxSg5HCbuMnFZbTOonDi7/3wIZc4geTWJKUbBJjDPMXFZfWPgpjFbrZSSZKcUo70GBgn/rtomLq//NbCsuAl+M4QyV4CsEMZujbzD5OU1U1t6Ig2s0fMhlDmCY5jBtZPpV6nSV88E7Joba1bJ4xAK/YFQPG/yMsszcKTPUilJVpfpvBTUTn9YYedUbbDAyHLjJvlrECr9l7YbXTtxf2CoBcpuTQrKbhuESu+geCUFQcE1YoZFJsGNLrudECq9g+LdFATFVouU3XQD5+38a3cVQqHZ4s8qi5RdVy3AjSw7HgipC6Hsu7Yn2Fqfpy1SdlkGJhL7C9USQqVvUBh9ciDnpl2P8ourydcNQqVvQOxKwVxKL4ttYmP0gM4ACJW+AbEnBQGhWqj8HjC4/HirsXEQKn0DYp/BAbHZYuV3jrYUxcjJ3QshVPquzjVaqA8tVobtUpCCdCOESt9g2G3w3fVRi5WhKwUjfQsgVHoGw2BtkMCoQOD+2lUWLMdfDBbqRQiVnoFwvsE1FP+u9hYsx+UGC7UcQqVnINxgcB9qj0XL8Q6DhVoDodIzEO4ijhgYCJssWo6jtCUpRpXjRgiVnoGwwKANG328bdFybGXwSN8WCIWNRsqJBy1ajqrBI33bIVR6BsLrBqccXWThstxkZD4fhEIeGg+ZN7ZwWf6fwQmymRAq/YLg30YOmVs8hesGA/ujO8x4aokdhNpooFDrLS7UUAOnIFioUyFU+gXBVwYGwesWF6qxgSN9eyBU+o5O3aUNGBxKYgDwXNd8G+xvuNOAgR3e4bcIgxLpn3X+lLZWqSxJgTDJBuX4fRJrJD7vuDd2jjVXQDTkjRS10wt/T+Ds/z6zbywSZfktSfBmoFzj/WrmU0psLVTAkoShWo4aB8k6TbB92mOs6Uq/26TcrtBx4MIRTZxd2v4U7xN3E2OI1lY7bM2WQoUJGN7PewJxL59STmzQMsj3ac2S37XtiTdo25O9w0sNeEjZJuVzCq/3Iv5BfKI1AVmS7ZowOzW4jFYRjxBTiU5W2PgTQgGjhctlbF8OCAYAIBQAEAoACAUAgFAAQCgAIBQAAEIBAKEAgFAAQCgAAIQCAEIBAKEAABAKAAgFAIQCAEIBAOLg/wHSDmfsVLKynAAAAABJRU5ErkJggg==\" ></div>\n" +
      "            <div class=\"col-md-6 text-center\">\n" +
      "                <div class=\"heading\">\n" +
      "                    <h3>Ninja Fruits Eaten</h3>\n" +
      "                    <p>By Ninja</p>\n" +
      "                </div>\n" +
      "            </div>\n" +
      "        </div>\n" +
      "        <div class=\"row\" >\n" +
      "            <div id=\"chart\" class=\"col-md-12\">\n" +
      "            </div>\n" +
      "        </div>\n" +
      "    </div>\n" +
      "</div>";

}