const state = {
  username: "",
  password: "",
  email: "",
};

const changeState = (e, name) => state[name] = e.target.value;

const isEmpty = obj => {
  for (var key in obj) {
    if (obj.hasOwnProperty(key))
      return false;
  }
  return true;
};

const generateErrorMessages = errors => {
  document.querySelectorAll('.error').forEach(error => error.parentNode.removeChild(error));
  for (let error in errors) {
    if (errors.hasOwnProperty(error)) {
      const p = document.createElement("p");
      p.className = "error";
      p.textContent = errors[error];
      document.querySelector('.' + error + '_label').appendChild(p);
    }
  }
};

const Register = async e => {
  e.preventDefault();

  const errors = {};

  if (state.username === "") errors.username = "Username field is empty!";
  if (state.password === "") errors.password = "Password field is empty!";
  if (state.email === "") errors.email = "Email field is empty!";

  if (isEmpty(errors)) {
    try {
      const response = await fetch("http://localhost:8080/Alita_Battle_war_exploded/register",
          {
            method: "POST",
            body: `username=${state.username}&pass=${state.password}&email=${state.email}`,
            headers:
                {
                  "Content-Type": "application/x-www-form-urlencoded"
                }
          });

      if (response.status === 200) {


        // Successful registration


      } else {

        throw new Error("Wrong information");
      }


    } catch (err) {
      console.log(err.response.data);
    }
  } else {
    generateErrorMessages(errors);
  }
};

const Login = async e => {

  e.preventDefault();

  const errors = {};

  if (state.username === "") errors.username = "Username field is empty!";
  if (state.password === "") errors.password = "Password field is empty!";

  if (isEmpty(errors)) {
    try {
      const response = await fetch("http://localhost:8080/Alita_Battle_war_exploded/login",
          {
            method: "POST",
            body: `username=${state.username}&pass=${state.password}`,
            headers:
                {
                  "Content-Type": "application/x-www-form-urlencoded"
                }
          });

      if (response.status === 200) {



        // Successful login


      } else {

        throw new Error("Wrong information");
      }


    } catch (err) {
      console.log(err.response.data);
    }
  } else {
    generateErrorMessages(errors);
  }
};