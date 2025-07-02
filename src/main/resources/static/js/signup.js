// Email
let isEmailChecked = false;

// Email 항목들 조합해서 통으로 만들기
function combineEmail() {
    const id = document.getElementById("email-id").value;
    const domain = document.getElementById("email-domain").value;
    document.getElementById("userEmail").value = id + "@" + domain;
}
// Email 중복 조회
function checkEmail() {
    combineEmail();

    const email = document.getElementById("userEmail").value;
    fetch(`/user/check_email?userEmail=${encodeURIComponent(email)}`)
        .then(response => response.json())
        .then(data => {
            if (data.duplicate) {
                alert("이미 사용 중인 이메일입니다. 다시 입력해주세요");
                isEmailChecked = false;
            } else {
                alert("사용 가능한 이메일입니다.")
                isEmailChecked = true;
            }
        });

}


// Password
const passwordInput = document.getElementById('password-input');
const feedback = document.getElementById('password-feedback');
const passwordCheck = document.getElementById('password-check');
const passwordCheckFeedback = document.getElementById('password-check-feedback');

// Password 8자리 이상인지 체크
passwordInput.addEventListener('input', function () {
    const len = passwordInput.value.length;

    if (len === 0) {
        feedback.textContent = "";
        feedback.style.visibility = 'hidden';
    } else if (len < 8) {
        feedback.textContent = "너무 짧음";
        feedback.className = "text-danger mt-1";  // 빨간색
        feedback.style.visibility = 'visible';
    } else {
        feedback.textContent = "✔️";
        feedback.className = "text-success mt-1";  // 초록색
        feedback.style.visibility = 'visible';
    }
});

// Password 확인과의 일치 여부
passwordCheck.addEventListener('input', function () {
    if(passwordCheck.value === ""){
        passwordCheckFeedback.textContent = "";
        passwordCheckFeedback.style.visibility = 'hidden';
    } else {
        if (passwordCheck.value !== passwordInput.value) {
            passwordCheckFeedback.textContent = "불일치"
            passwordCheckFeedback.className = "text-danger mt-1";
            passwordCheckFeedback.style.visibility = 'visible';
        } else {
            passwordCheckFeedback.textContent = "✔️";
            passwordCheckFeedback.className = "text-success mt-1";
            passwordCheckFeedback.style.visibility = 'visible';
        }
    }
});


// nickname
let isNicknameChecked = false;
// 닉네임 중복 조회
function checkNickname() {
    const nickname = document.querySelector("input[name='userNickname']").value;

    fetch(`/user/check_nickname?userNickname=${encodeURIComponent(nickname)}`)
        .then(response => response.json())
        .then(data => {
            if (data.duplicate) {
                alert("이미 사용 중인 닉네임입니다. 다시 입력해주세요.");
                isNicknameChecked = false;
            } else {
                alert("사용 가능한 닉네임입니다.");
                isNicknameChecked = true;
            }
        });
}

//페이지 변경
function nextStep() {
    const step1 = document.getElementById("step1");
    const step2 = document.getElementById("step2");

    // step1 나가기
    step1.classList.remove("active");
    step1.classList.add("exit-left");

    // step2 들어오기
    step2.style.display = "block";

    setTimeout(() => {
        step2.classList.add("active");
        step1.classList.remove("exit-left");
        step1.style.display = "none";
    }, 400); // transition 시간과 동일
}

function prevStep() {
    const step1 = document.getElementById("step1");
    const step2 = document.getElementById("step2");

    step2.classList.remove("active");
    step2.classList.add("exit-right");

    step1.style.display = "block";
    setTimeout(() => {
        step1.classList.add("active");
        step2.classList.remove("exit-right");
        step2.style.display = "none";
    }, 400);
}

// 폼 제출 관련
document.querySelector('form').addEventListener('submit', function(e) {
    e.preventDefault();

    const userEmail = document.querySelector('input[name="userEmail"]').value.trim();
    const userPassword = document.querySelector('input[name="userPassword"]').value.trim();
    const userPasswordCheck = document.querySelector('input[name="userPasswordCheck"]').value.trim();
    const userNickname = document.querySelector('input[name="userNickname"]').value.trim();
    const userName = document.querySelector('input[name="userName"]').value.trim();
    const userBirth = document.querySelector('input[name="userBirth"]').value.trim();
    const userPhoneNumber = document.querySelector('input[name="userPhoneNumber"]').value.trim();

    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    const phoneRegex = /^[0-9]{10,11}$/;

    // const csrfToken = document.querySelector('input[name="_csrf"]').value;
    // const csrfHeader = document.querySelector('input[name="_csrf"]').getAttribute('name');

    // 유효성 검사
    if (!userEmail) {
        alert("이메일을 입력하세요.")
        return;
    } else if(!emailRegex.test(userEmail)){
        alert("이메일이 유효하지 않습니다.");
        return;
    } else if (!isEmailChecked) {
        alert("이메일 중복 여부를 확인해주세요.");
        return;
    }

    if (!userPassword) {
        alert("비밀번호를 입력하세요.");
        return;
    } else if (userPassword.length < 8){
        alert("비밀번호는 8자 이상이어야 합니다.");
        return;
    } else if (userPassword !== userPasswordCheck) {
        alert("비밀번호와 비밀번호 확인란의 값이 다릅니다.");
        return;
    }

    if (!userNickname) {
        alert("닉네임을 입력하세요.");
        return;
    } else if (!isNicknameChecked) {
        alert("닉네임 중복 여부를 확인해주세요.");
    }

    if (!userName) {
        alert("이름을 입력하세요.");
        return;
    }

    if (!userBirth) {
        alert("생년월일을 입력하세요.");
        return;
    }

    if (!userPhoneNumber) {
        alert("");
        return;
    } else if (!phoneRegex.test(userPhoneNumber)){
        alert("전화번호는 10~11자리의 숫자만 입력하세요.");
        return;
    }

    // 유효성 검사 통과 시 전송
    const formData = {
        userEmail: userEmail,
        userPassword: userPassword,
        userNickname: userNickname,
        userName: userName,
        userBirth: userBirth,
        userPhoneNumber: userPhoneNumber
    };

    fetch('/user/signup', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            // [csrfHeader]: csrfToken
        },
        body: JSON.stringify(formData)
    })
        .then(response => {
            if (response.redirected) {
                alert("회원가입이 완료되었습니다.")
                window.location.href = response.url;
            } else {
                return response.text();
            }
        })
        .then(data => {
            if(data){
                if(data.startsWith('redirect:')){
                    window.location.href = data.substring(9);
                } else if (data.includes('error')) {
                    window.location.href = data;
                }
            }
        })
        .catch(error => {
            alert("회원가입에 실패했습니다.")
            console.error('Error:', error);
        });
});