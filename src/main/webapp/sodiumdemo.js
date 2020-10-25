/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


async function postData(url = '', data = {}) {
    const response = await fetch(url, {
        method: 'POST',
        mode: 'cors',
        cache: 'no-cache',
        credentials: 'same-origin',
        headers: {
            'Content-Type': 'application/json'
        },
        redirect: 'follow',
        referrerPolicy: 'no-referrer',
        body: JSON.stringify(data)
    });
    if (!response.ok) {
        throw Error(response.statusText);
    }
    return response.json();
}

async function getData(url = '') {
    const response = await fetch(url);
    if (!response.ok) {
        throw Error(response.statusText);
    }
    return response.json();
}


async function getNewKey() {
    let response = await getData('./generatepublickey');
    if (response.status === 1) {
        return response.data.publicKey;
    } else {
        throw response.errorMessage;
    }
}

async function encrypt(plaintext, serverPublic) {
    if (!sodium)
        sodium = await SodiumPlus.auto();
    let publicKey = X25519PublicKey.from(serverPublic, 'hex');
    let ciphertext = await sodium.crypto_box_seal(plaintext, publicKey);
    let cipherTextHex = await sodium.sodium_bin2hex(ciphertext);
    return cipherTextHex;
}


async function decrypt(cipherTextHex) {
    let response = await postData("./decrypt", {cipherText: cipherTextHex});
    if (response.status === 1) {
        return response.data.decrypted;
    } else {
        throw response.errorMessage;
    }
}
(async function () {
    if (!window.sodium)
        window.sodium = sodium = await SodiumPlus.auto();
});


window.addEventListener('DOMContentLoaded', (event) => {
    let btnGenKey = document.getElementById('btnGenKey');
    let btnEncrypt = document.getElementById('btnEncrypt');
    let btnDecrypt = document.getElementById('btnDecrypt');


    btnGenKey.onclick = () => {
        getNewKey().then((key) => {
            document.getElementById('lblKey').textContent = key;
        }).catch((err) => {
            alert(err);
            document.getElementById('lblKey').textContent = "";
        });
    };

    btnEncrypt.onclick = () => {
        let txtPlain = document.getElementById('txtPlain').value;
        let serverPublic = document.getElementById('lblKey').textContent;
        if (txtPlain.trim() === "") {
            alert('Please enter text to encrypt.');
            return;
        }
        if (serverPublic.trim() === "") {
            alert('Please get key.');
            return;
        }
        encrypt(txtPlain, serverPublic).then((cipherTextHex) => {
            document.getElementById('txtCipher').value = cipherTextHex;
        }).catch((err) => {
            alert(err);
            document.getElementById('txtCipher').value = "";
        });
    };

    btnDecrypt.onclick = () => {
        let txtCipher = document.getElementById('txtCipher').value;
        if (txtCipher.trim() === "") {
            alert('Please enter text to decrypt.');
            return;
        }
        decrypt(txtCipher).then((decrypted) => {
            document.getElementById('txtDecrypted').value = decrypted;
        }).catch((err) => {
            alert(err);
            document.getElementById('txtDecrypted').value = "";
        });
    };

    btnGenKey.click();

});